package anshay.notebook.learn.java8;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

/**
 * java8新特性测试
 *
 * @author machao
 * @date 2021/4/9
 */
public class Test {


    /**
     * 代码参数化
     */
    public void A() {
        List<Apple> list = new ArrayList<>();
        list.removeIf(v -> "red".equals(v.getColor()));
        // list.sort(Comparator.comparing((v1,v2) ->v1.getNum()- v2.getNum());
        list.sort(comparingInt(Apple::getNum));
        list.sort(comparingInt(Apple::getNum).reversed());
        list.sort(Comparator.comparing(Apple::getNum));
        List<Apple> filter = filter(list, (Apple v) -> "red".equals(v.getColor()));

        //IntPredicate、IntStream 更具体的实现，指定了Int，对于基础类
        // 解决了需要拆装箱的性能消耗
        IntPredicate intPredicate = value -> {
            return false;
        };
        IntStream intStream = list.stream().mapToInt(Apple::getNum);

        //这样才可以直接使用sum方法
        int sum = intStream.sum();
        Integer reduce = list.stream().map(Apple::getNum)
                .reduce(0, Integer::sum);
        // list.stream().count()

        Predicate<Apple> redApple = v -> "red".equals(v.getColor());
        Predicate<Apple> and = redApple.and(v -> v.getColor() != null);
        Predicate<Apple> redAndHeavyAppleOrGreen =
                redApple.and(a -> a.getWeight() > 150).or(a -> "green".equals(a.getColor()));


    }

    /**
     * 代码参数化过滤方法
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        list.removeIf(p);
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;

    }

    /**
     * 排序
     */
    public void B() {
        List<Apple> list = new ArrayList<>();
        list.removeIf(v -> "red".equals(v.getColor()));
        list.sort(Comparator.comparing(v -> -v.getNum()));
        list.sort((v1, v2) -> v1.getNum() - v2.getNum());
    }

    public void C() {
        List<String> strings = Arrays.asList("123", "232");
        strings.sort(String::compareToIgnoreCase);

    }

    /**
     * 函数方法编程
     */
    public void D() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
        integrate(x -> x + 10, 10, 20);
    }

    public double integrate(DoubleFunction<Double> f, double a, double b) {

        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }

    @Builder
    @Data
    public static class Apple {
        private String name;
        private double weight;
        private String color;
        private int num;
    }

    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();

        list.add(Apple.builder().color("red1").num(1).build());
        list.add(Apple.builder().color("red2").num(2).build());
        list.add(Apple.builder().color("red3").num(3).build());
        list.add(Apple.builder().color("red4").num(4).build());

        Set<Object> seen = new HashSet<>();
        Set<String> collect = list.stream().filter(v -> v.getWeight() > -10)
                // .filter()
                .sorted(Comparator.comparing(v -> v.getNum()))
                .map(v -> v.getName())
                //这里是调用的流对象的hashCode和equals方法进行去重复
                .distinct()
                .limit(3)
                .collect(Collectors.toSet());
        int a = 0;
    }

    /**
     * 流式编程
     */
    public void myStream() {
        List<Apple> list = new ArrayList<>();
        //常规聚合

        // Set<Object> seen = ConcurrentHashMap.newKeySet();
        Set<Object> seen = new HashSet<>();
        Set<String> collect = list.parallelStream().filter(v -> v.getWeight() > 10)
                .filter(v -> seen.add(v.getName()))
                .sorted(Comparator.comparing(v -> v.getWeight()))
                .map(v -> v.getName())
                //这里是调用的流对象的hashCode和equals方法进行去重复
                .distinct()
                .limit(3)
                .collect(Collectors.toSet());

        //分组
        Map<String, List<Apple>> collect1 = list.stream().collect(Collectors.groupingBy(v -> v.getName()));


        //流的扁平化——flatMap
        String[] arrayOfWords = {"Goodbye", "World"};
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()

                .map(String::length).collect(toList());
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        List<String> uniqueCharacters = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());

        int a = 1;
        //获取指定类型的流(1-100间的偶数)
        IntStream range = IntStream.rangeClosed(0, 100).filter(v -> v % 2 == 0);
        range.forEach(System.out::println);

        IntStream intStream1 = IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0);
        Stream<Integer> boxed = IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed();

        Stream<int[]> stream = IntStream.rangeClosed(0, 100)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});

        //由值创建流
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        Stream<? extends Serializable> stream1 = Stream.of("234", 234, 9.0);
        Stream<Object> empty = Stream.empty();
        Stream<String> stream2 = Arrays.stream(new String[]{"123", "424", "wqrw"});

        Stream<Double> doubleStream = Stream.generate(Math::random).limit(5);

    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }


}
