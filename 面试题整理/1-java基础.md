## 一、Java 基础知识
#### 1.JDK 和 JRE 有什么区别？
**java development kit**：java开发工具包，包括了jre、编译java源码的编译器javac、java程序调试和分析的如jconsole、jvisualvm等软件工具，还包含了java程序编写所需的文档和demo例子。
**java Runtime  environment** ：包含java虚拟机、java基础类库。是使用java语言编写的程序所需要的软件环境，用于运行java程序。

#### 2. equals 和 == 的区别是什么？
java中的数据类型，可分为两类： 
- 基本数据类型，byte,short,char,int,long,float,double,boolean。只有==方法，没有equals方法，==比较的是他们的值。
- 类类型  
    1. == 比较的是内存地址，除同一个new出来的对象比较结果为true外，其他都为false。 
    2. Object中定义了equals方法——>
```java 
public boolean equals(Object obj) {    
    return (this == obj);
}
```
  其核心也是调用==方法进行比较。但在一些类库，如String,Integer,Date等类当中equals有其自身的实现，覆盖后进行了内容的比较，而不再是比较类在堆内存中的存放地址。equals方法本身设计出来就是为了让人去复写的。
 
为了方便理解，下面给部分源码范例和简单调用。
*String中equals实现*  
```java
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String anotherString = (String)anObject;
            int n = value.length;
            if (n == anotherString.value.length) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
```
*String调用*
```java
    String s11 = "12";
    String s12="3";
    String s1=s11+12;

    String s2 = new String("123");
    if(s1==s1) {
        System.out.println("==："+true);
    }else {
        System.out.println("==："+false);
    }

    if(s1.equals(s1)) {
        System.out.println("equals："+true);
    }else {
        System.out.println("equals："+false);
    }
//运行结果
//==：true
//equals：true
```
  
*List中的equals实现* 
```java
public boolean equals(Object o) {
    if (o == this){
        return true;
    } 
    if (!(o instanceof List)){
        return false;
    }
    ListIterator<E> e1 = listIterator();
    ListIterator<?> e2 = ((List<?>) o).listIterator();
    while (e1.hasNext() && e2.hasNext()) {
        E o1 = e1.next();
        Object o2 = e2.next();
        if (!(o1==null ? o2==null : o1.equals(o2))){
            return false;
        }
    }
        return !(e1.hasNext() || e2.hasNext());
}
```
List调用
```java
    List a = new ArrayList<>();
    a.add(1);
    List b = new ArrayList<>();
    b.add(1);
    if(a==b) {
        System.out.println("==："+true);
    }else {
        System.out.println("==："+false);
    }
// 运行结果
// ==：false
// equals：true
```
*Date中equals*
```java
public boolean equals(Object obj) {
        return obj instanceof Date && getTime() == ((Date) obj).getTime();
    }
```
*Date调用*
```java
    //a和b时间差极小，编译器会认为是同一个时间
    Date a = new Date();
    Date b = new Date();
    if(a==b) {
        System.out.println("==："+true);
    }else {
        System.out.println("==："+false);
    }

    if(a.equals(b)) {
        System.out.println("equals："+true);
    }else {
        System.out.println("equals："+false);
    }
//运行结果
//==：false
//equals：true
```


#### 3.两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？
不对。
equals方法默认是比较两者的内存地址，封装类复写后为内容比较。
hashCode方法给对象返回一个hashCode值，用于hashtables，如HashMap。
性质;
- 在一个java应用的执行期间,如果一个对象提供给equls作比较的信息没被修改，则该对象多次调用hashCode方法始终放回同一个integer。
- 如果两个对象根据equsl方法比较是相等的，那么调用二者各自的hashCode方法必须产生同一个integer结果。
- 并不要求根据equals方法不相等的两个对象，调用二者各自的hashCode方法必须产生不同integer结果。然而，程序员应该意识到对于不同的对象产生不同的integer结果，有助于提高hash table的性能。

假如两个Java对象A和B，A和B不相等（eqauls结果为false），但**A和B的哈希码相等，将A和B都存入HashMap时会发生哈希冲突**，也就是A和B存放在HashMap内部数组的位置索引相同。这时HashMap会在该位置建立一个链接表，将A和B串起来放在该位置，显然，该情况不违反HashMap的使用原则，是允许的。当然，哈希冲突越少越好，尽量采用好的哈希算法以避免哈希冲突。

部分细节可以参考[equals()与hashCode()方法详解](https://www.cnblogs.com/Qian123/p/5703507.html)。
- 这里考察equals和hashcode方法规范，以及hashmap实现原理。如果有多余时间，需要看看HashMap源码，理解其实现原理。

#### 4.final 在 java 中有什么作用？
*变体：说出java中final、finaly、finalize 的区别。*
- final:修饰变量时表示不可被改变（如果变量指向一个值的引用，那么变量是可以更改的，但是被引用的值不可更改），修饰方法时表示不可被复写，修饰类时表示不可被继承。
- finally：在try-catch代码块中，不管tyr块中的异常是否抛出，finally中的代码都能得到执行。主要用于清理try语句块中使用的资源。扩展：对于无垃圾回收和析构函数自动调用机制的语言来说，无论try语句块里发了什么，内存总能得到释放。
- finalize：finalize()用于判断对象是否可被回收。在进行垃圾回收时，对象需要至少进行两次标记过程。第一次标记的筛选依据即是否有必要执行finalize方法。对象没有覆盖或者已经被调用过，则视为没有必要执行。如果有必要执行，这个对象会被仿佛在一个叫做F-Queue的队列中。然后稍后油一个有虚拟机自动i建瓯i的、低优先级的Finalizer线程去执行他。但是在Finaliz()方法中，对象也可以实现自救，把this关键字赋值给某个类变量或者对象成员变量，即在方法体中重新被引用移出“即将回收”的集合。但是此方法不推荐使用。

#### 5.java 中的 Math.round(-1.5) 等于多少？
该方法返回最接近或int/long整型，返回给定方法的参数类型。相当于四舍五入。
考察常用的Math类方法。


#### 6.String 属于基础的数据类型吗？
java中8个基础数据类型：int、byte、short、long、boolean、char、double、float。
String 是java lang包下的类，是引用数据类型，定义语句如下
public final class String extends Object implements Serializable, Comparable<String>, CharSequence
继承于Object，实现Serializable、Comparable、CharSequence接口。

扩展：基本数据类型存储在栈上，引用类型存储在堆上。基本数据类型都有自己对应的封装类，能够自动拆箱装箱，但是基本数据类型有默认值，封装类型没有。命名上，基本数据类型都是小写，封装类型为：int-Integer、byte-Byte、short-Short、long-Long、boolean-Boolean、char-Character、double-Doubler、float-Float。

#### 7.java 中操作字符串都有哪些类？它们之间有什么区别？
String(字符串常量)、
StringBuilder(字符串变量，线程安全)、
StringBuffer(字符串变量，线程不安全)。

都是final类，不允许被继承。
String是不可变的对象，每次对String类进行改变得时候都会生成一个新得Sting对象，然后将指针指向新得String对象。经常改变字符串长度尽量不要使用String，每次使用都会对系统性能产生影响。放内存中引用得兑现多了以后，JVM就会开始i共做，性能就会降低。

StringBuilder和StringBuffer，每次都会对对象本身进行操作，而不是生成新的得对象并改变对象引用。StringBuffer虽然是线程安全的，但是使用意义不大，并且会拖慢程序速度。其线程安全仅仅是保证jvm不抛出异常知识顺利的往下执行而已，不保证逻辑正确和调用顺序正确。大多时候，我们需要的不仅是线程安全，而是锁。在jdk1.5时，提供了非线程安全的StringBuffer实现，并命名为StringBuilder。从jdk1.5开始，javac把所有用加号连接的String运算都隐式的改写成StringBuilder，不再因此有内存损耗。但在循环中的字符串拼接，每次循环都会新建一个StringBuilder，也会造成损耗。在这些场景中最好自己创建StringBuilder对象。

#### 8.String str="i"与 String str=new String("i")一样吗？

- String str="i"：现在常量池(方法区/永久代)里找有没有“i”这个字符串，如果有就返回这个字符串。没有的话就去创建这个常量，并返回这个引用。
- String str=new String("i")是在java堆上创建一个value为“i”的String对象，jdk1.6及以前的版本会将创建的对象复制到方法区中然后返回方法区中的这个对象的引用，jdk1.7及以后的版本开始“去永久代化”，不会复制到方法区，直接返回java堆中的这个对象的引用。

两者内容一样，==比较为false，equals比较为true，hashcode一样。存储位置不同，前者存储于永久代，后者存储于java堆(jdk1.7及以后)。  

#### 9.如何将字符串反转？
考察StringBuilder的方法使用,自行查阅API并熟练。
```java
String string="123";
String reverse = new StringBuffer(string).reverse().toString();
System.out.println("字符串反转前:"+string);// 字符串反转前:123
System.out.println("字符串反转后:"+reverse);// 字符串反转后:321
```
常用方法
append(T t):添加参数的内容进来
charAt(int index)：返回所在位置的字符
capacity():返回容量
delete(int start, int end) ：删除start到end(前闭后开)的子字符串。
substring(int start, int end) :返回start到end(前闭后开)的子字符串。
toString():……

#### 10.String 类的常用方法都有那些？
考察常规使用，自行查阅API。
charAt(int index) 返回指定索引处的 char 值。
compareTo(String anotherString) 按字典顺序比较两个字符串。
compareToIgnoreCase(String str) 按字典顺序比较两个字符串，不考虑大小写。
contains(CharSequence s) 当且仅当此字符串包含指定的 char 值序列时，返回 true。
endsWith(String suffix) 测试此字符串是否以指定的后缀结束。
equals(Object anObject) 将此字符串与指定的对象比较。
hashCode()  返回此字符串的哈希码。 
int indexOf(int ch) 返回指定字符在此字符串中第一次出现处的索引。 
length() 返回此字符串的长度。
replace(char oldChar, char newChar) 返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的。
split(String regex) 根据给定正则表达式的匹配拆分此字符串。
……

#### 11.抽象类必须要有抽象方法吗？
用abstract修饰的类就是抽象类，并不是说抽象类中必须有抽象方法，即使一个类中的方法全部实现过，也可以用abstract修饰为抽象类。所以抽象类不一定都有抽象方法。
- 抽象类不可被实例化，只能通过非抽像子类继承后才能实例化。

*变体：请说出抽象类与接口的异同点。*


#### 12.普通类和抽象类有哪些区别？
抽象类本身不可以被实例化，只能被非抽线子类实例化。抽象类可以有抽象方法，需要通过子类覆写对应的抽象方法来实现。普通类不可以有抽象方法。

#### 13.抽象类能使用 final 修饰吗？
不可以，抽象类只能通过子类继承来实现。不能继承即表示不可用。

#### 14.接口和抽象类有什么区别？
抽象类：abstract修饰的类。
对应的抽象方法：仅有声明没有方法体的方法。
```java public abstract void f();    //没有内容  ```
继承抽象类的子类需要覆写父类所有抽象方法。

接口时抽象类的以中特殊形式，使用interface修饰，用于说明一个约定，实现此接口的类需要实现接口所定义的所有方法。

- 抽象类是由子类具有相同的一类特征抽象而来，也可以说是其基类或者父类
- 抽象方法必须为 public 或者 protected（因为如果为 private，则不能被子类继承，子类便无法实现该方法），缺省情况下默认为 public
- 抽象类不能用来创建对象
- 抽象方法必须由子类来实现
- 如果一个类继承于一个抽象类，则子类必须实现父类的抽象方法，如果子类没有实现父类的抽象方法，则必须将子类也定义为抽象类
- 抽象类还是很有用的重构工具，因为它们使得我们可以很容易地将公共方法沿着继承层次结构向上移动


接口是抽象类的延伸，它可以定义没有方法体的方法，要求实现者去实现。

- 接口的所有方法访问权限自动被声明为 public
- 接口中可以定义“成员变量”，会自动变为 public static final 修饰的静态常量 
- 可以通过类命名直接访问：ImplementClass.name
- 不推荐使用接口创建常量类
- 实现接口的非抽象类必须实现接口中所有方法，抽象类可以不用全部实现
接口不能创建对象，但可以申明一个接口变量，方便调用
- 完全解耦，可以编写可复用性更好的代码

一个类只能继承一个抽象类，但可以实现多个接口。

#### 15.java 中 IO 流分为几种？

#### 16.BIO、NIO、AIO 有什么区别？

#### 17.Files的常用方法都有哪些？

--- 
## 二、容器
[关于java集合的小抄](http://calvin1978.blogcn.com/articles/collection.html)
#### 18.java 容器都有哪些？
|Collection 
|　　├List 
|　　│-├LinkedList 随机访问慢，插入移除快
|　　│-├ArrayList 长于随机访问元素，但是在List中插入和移除元素时较慢
|　　│-└Vector 
|　　│　└Stack 
|　　├Set 
|　　│├HashSet 
|　　│├TreeSet 
|　　│└LinkedSet 
| 
|Map 
　　├Hashtable 
　　├HashMap 
　　└WeakHashMap


#### 19.Collection 和 Collections 有什么区别？

- Collection是集合接口（集合类的顶级接口），它提供了对集合进行基本操作的接口方法。Collection接口的意义是为各种具体的集合提供了最大化的统一操作方式。直接继承接口有List和Set。
 Collection   
├List   
│├LinkedList   
│├ArrayList   
│└Vector   
│　└Stack   
└Set

- Collections是集合类的一个工具类，其中提供了一些列静态方法，用于对集合中元素进行排序、搜索以及线程安全等各种操作。[Collections部分API。](https://www.cnblogs.com/cathyqq/p/5279860.html)


#### 20.List、Set、Map 之间的区别是什么？
- List:
1. 允许重复的对象；
2. 可以插入多个null元素；
3. 有序容器，保证每个元素的插入顺序，输出顺序就是插入顺序。
- Set：
1. 不可重复；
2. 无序容器（TreeSet通过Comparator或Comparable维护了一个排序顺序）；
3. 只允许一个null元素；
4. Set接口常用的实现类是HashSet、LinkedHashSet以及TreeSet，最多的是基于HashMap实现的HashSet；TreeSet实现了Sorted接口是一个有序容器。
- Map：
1. Map不是Collection的接口或实现类，是一个单独的接口；
2. 每个Entry都有两个对象，Key和Value，Key不可重复。
3. TreeMap通过Comparator或者Comparable维护了一个排序顺序；
4. Map中的Key和value都可以为null，但是key不能重复即只能有一个key为null。

#### 21.HashMap 和 Hashtable 有什么区别？
- HashMap：key和value都可以为null，线程不安全。
- Hashtable：不允许null作为key或者value，线程安全，但是已经不推荐使用。
他们拥有相同的接口。如果在单线程中使用HashMap，需要并发访问时使用ConcurrentHashMap

#### 22.如何决定使用 HashMap 还是 TreeMap？
**（考察HashMap和TreeMap的实现原理）**
TreeMapy内部维护一个排序顺序，HashMap没有。HashMap的插入读取速度更高一些。

HashMap基于Hash表实现，使用HashMap要求添加的键明确定义了hashCode()和equals()方法。为了优化HashMap的使用，可以调优初始容量和负载因子。HashMap使用于在map中插入、删除和定位元素，其结果是未排序的。
TreeMap基于红黑树实现，没有调优选项，该树总是处于平衡状态。使用于按自然顺序或自定义顺序遍历键（key）,TreeMap实现了SortMap接口，能够把保存的额近路根据键值的升序排序，也可以指定排序的比较器。适用于增加和快速创建。

#### 23.说一下 HashMap 的实现原理？
[HashMap工作原理及实现细节](https://yikun.github.io/2015/04/01/Java-HashMap%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86%E5%8F%8A%E5%AE%9E%E7%8E%B0/)
HashMap是基于Map接口的实现，允许null键/值、非同步、不保证有序（如插入的顺序）、也不保证序不随时间变化。HashMap存储着Entry(hash,key,value,next)对象。
- **HashMap的工作原理**：通过hash的方法，通过put和get存储和获取对象，存储对象时，我们将key/value传给put方法时，它调用hashCode计算hash从而得到bucket位置，进一步存储，HashＭap会跟根据当前buket的占用情况自动调整容量（超过LoadFactor则resize为原来的2倍）。获取对象时，我们将k传给get，它调用hasCode计算hash从而得到bucket的位置，并进一步调用eauals()方法确定键值对。如果发生碰撞的时候，HashMap通过链表将产生碰撞冲突的元素组织起来。在java8中，如果一个bucket中碰撞冲突的元素超过某个限制(默认是8)，则使用红黑树来替换链表，从而提高速度。
- **ge和put的原理？equals()和hashCode()的作用？**:通过key的hashCode()进行hashing，并计算下表(n-1 & hash),从而获得buckets的位置。如果产生碰撞，则利用key.equals()方法去链表或树种查找相应的节点。
- **hash的如何实现？为什么要这样实现？**：在java8的实现种，是通过hashCode()的高16位异或低16位实现的：(h=k.hashCode())^(h>>>16))，主要从速度、功效、质量来考虑的，这样可以在bucket的n比较小时，也能保证考虑高低bit都参与到hash的计算中，同时不会有太大的开销。
- **如果HashMap的到校超过了负载因子(Load factor)定义的容量，怎么办？**：如果超过了负载因子（默认0.75），则会中新resize一个原来长度两倍的HashMap，并且重新调用hash方法。

#### 24.说一下 HashSet 的实现原理？
[HashSet实现原理](https://zhangshixi.iteye.com/blog/673143)
1. **概念**
HashSet实现Set接口，由哈希表（实质是一个HashMap实例）支持。它不保证set的迭代顺序，特别是它不保证该顺序恒久不变。此类允许使用null元素。
2. **HashSet实现**
对于HasSet来说，它是基于HashMap实现的，HashSet底层使用HashMap来保存所有元素，因此其实现比较简单， 相干操作都是直接调用HashMap的相关方法来完成。具体可以阅读HashSet源码。

#### 25.ArrayList 和 LinkedList 的区别是什么？
[ArrayList和LinkedList的区别](https://blog.csdn.net/eson_15/article/details/51145788)
1. ArrayList是实现了基于动态数组的数据结构，而LinkedList是基于链表的数据结构。
2. ArrayList的所有数据是在同一个地址上,而LinkedList的每个数据都拥有自己的地址。对于随机访问get和set，ArrayList速度要优于后者，因为LinkedList要移动指针。
3. 对于添加add和删除remove操作，一般会说LinkedList要快，因为ArrayList要移动数据。但实际上，对于添加和删除，两者并不能准确说明哪个快。具体参考源码方法。
在源码中，ArrayList想要get(int index)元素时，直接返回index位置上的元素，而LinkedList需要通过for循环来查找。虽然LinkedList在查找方法上做了优化，当index < size/2 时，从左边开始查找，反之从右边。但是还是比ArrayList慢，这是毋庸置疑的。
ArrayList想要在指定位置插入或者删除元素时，主要耗时的时System.arraycopy动作，会移动index后面所有的元素；LinkedList耗时主要是先通过for循环找到index，然后直接插入或者删除(这个动作只需要前后两个对象的指针即可)。这就导致了两者并非一定谁快谁慢。主要影响因素时插入的数据量和插入的位置。尤其时在List末端添加数据时，二者效率不相上下。具体细节查看上述链接。
#### 26.如何实现数组和 List 之间的转换？
[javaList和数组相互转换方法](https://blog.csdn.net/zjx2016/article/details/78273192)
- List转数组
1. 使用for循环
2. 使用使用toArray()
```java
List<String> list = new ArrayList<String>(){{add("aa");add("bb");add("cc");}};
String[] array = list.toArray(new String[list.size()]);
for(int i=0;i<array.length;i++){
    System.out.println(array[i]);
}
```
- 数组转List
1. 循环
2. asList方法
```java
ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(array));
//如果使用下面这个方法，则返回的是Arrays的一个私有静态类，list1长度是固定的，在增加删除元素时会报UnsupportedOperationException。
List<String> list1 = Arrays.asList(array);
```
3. 使用Collections.addAll()
```java
List<String> list2 = new ArrayList<String>(arrays.length);
Collections.addAll(list2, arrays);
```

#### 27.ArrayList 和 Vector 的区别是什么？
List接口一共有三个实现类：ArrayList、Vector、LinkedList。List用于存放多个元素，能够维护元素的次数，并且允许元素的重复。三个实现类的相关区别如下。
1. ArrayList是最常用的List实现类，内部是通过数组实现的，它允许对元素进行快速随机访问。数组的缺点是每个元素之间不能有间隔，当数组大小不满足时需要增加存储能力，就要将已有数组的数据复制到新的存储空间中。当ArrayList的中间位置插入或者删除元素时，需要对数组进行复制、移动，代价较高，因此，它适合随机查找和遍历，不适合插入和删除。
2. Vector和ArrayList一样，也是通过数组实现的，不同的是它支持线程的同步。同一时刻只能有一条线程可以写Vector，避免多线程同时写而引起的不一致性。但实现同步需要很高的花费，因此访问速度比ArrayList慢。
3. LinkedList是用链表结构存储数据的，适合数据的插入和删除，随机访问和遍历速度较慢。另外它提供了List接口中没有定义的方法，专门用于操作表头和标为元素，可以当作堆栈、队列和双向队列来使用。

**区别**：
1. ArrayList在内存不够时默认是扩展50% + 1个，Vector是默认扩展1倍。
2. Vector提供indexOf(obj, start)接口，ArrayList没有。
3. Vector属于线程安全级别的，但是大多数情况下不使用Vector，因为线程安全需要更大的系统开销。

#### 28.Array 和 ArrayList 有何区别？
1. Array可以包含基本类型和对象类型，ArrayList只能包含对象类型。
2. Array大小是固定的，ArrayList大小是动态变化的。
3. ArrayList提供了更多的方法和特性，如addAll(),removeAll(),iterator()等等。对于基本类型数据，集合使用自动装箱来减少编码工作量。

#### 29.在 Queue 中 poll()和 remove()有什么区别？
*考察Queue的api。*
1. queue的增加元素方法add和offer的区别在于，add方法在队列满的情况下将选择抛异常的方法来表示队列已经满了，而offer方法通过返回false表示队列已经满了；在有限队列的情况，使用offer方法优于add方法；
2. remove方法和poll方法都是删除队列的头元素，remove方法在队列为空的情况下将抛异常，而poll方法将返回null；
3. element和peek方法都是返回队列的头元素，但是不删除头元素，区别在与element方法在队列为空的情况下，将抛异常，而peek方法将返回null.

#### 30.哪些集合类是线程安全的？
Vector、Hashtable、ConcurrentHashMap、Stack（栈，继承于Vector）

#### 31.迭代器 Iterator 是什么？

#### 32.Iterator 怎么使用？有什么特点？

#### 33.Iterator 和 ListIterator 有什么区别？

#### 34.怎么确保一个集合不能被修改？

---
## 三、多线程
#### 35.并行和并发有什么区别？
- 并发性(concurrency):又称共行性，是指能处理多个同时性活动的能力，并发事件不一定要同一时刻发生。
- 并行(parallenlism):是只同时发生的两个并发事件，具有并发的含义，而并发不一定并行。

举例子：并发可以看作屏幕的电子扫描，速度快可以看成同时出现一个画面，其实是一个点快速的扫描出现的。并行可以看作平行线，同一位置同时出现。
![并发和并行](https://i.loli.net/2019/03/14/5c89e5131f648.jpg)

#### 36.线程和进程的区别？
(待总结)线程属于同一个程序，信息共享。
进程相当于操作系统上的不同进程，信息不共享。
[进程与线程的区别](https://blog.csdn.net/mxsgoden/article/details/8821936)

#### 37.守护线程是什么？
#### 38.创建线程有哪几种方式？
#### 39.说一下 runnable 和 callable 有什么区别？
40.线程有哪些状态？
41.sleep() 和 wait() 有什么区别？
42.notify()和 notifyAll()有什么区别？
43.线程的 run()和 start()有什么区别？
44.创建线程池有哪几种方式？
45.线程池都有哪些状态？
46.线程池中 submit()和 execute()方法有什么区别？
47.在 java 程序中怎么保证多线程的运行安全？
48.多线程锁的升级原理是什么？
49.什么是死锁？
50.怎么防止死锁？
51.ThreadLocal 是什么？有哪些使用场景？ 
52.说一下 synchronized 底层实现原理？
53.synchronized 和 volatile 的区别是什么？
54.synchronized 和 Lock 有什么区别？
55.synchronized 和 ReentrantLock 区别是什么？
56.说一下 atomic 的原理？

#### 四、反射
57.什么是反射？
58.什么是 java 序列化？什么情况下需要序列化？
59.动态代理是什么？有哪些应用？
60.怎么实现动态代理？

#### 五、对象拷贝
61.为什么要使用克隆？
62.如何实现对象克隆？
63.深拷贝和浅拷贝区别是什么？

#### 六、Java Web
64.jsp 和 servlet 有什么区别？
65.jsp 有哪些内置对象？作用分别是什么？
66.说一下 jsp 的 4 种作用域？
67.session 和 cookie 有什么区别？
68.说一下 session 的工作原理？
69.如果客户端禁止 cookie 能实现 session 还能用吗？
70.spring mvc 和 struts 的区别是什么？
71.如何避免 sql 注入？
72.什么是 XSS 攻击，如何避免？
73.什么是 CSRF 攻击，如何避免？


七、异常
74.throw 和 throws 的区别？
75.final、finally、finalize 有什么区别？
76.try-catch-finally 中哪个部分可以省略？
77.try-catch-finally 中，如果 catch 中 return 了，finally 还会执行吗？
78.常见的异常类有哪些？


八、网络
79.http 响应码 301 和 302 代表的是什么？有什么区别？
80.forward 和 redirect 的区别？
81.简述 tcp 和 udp的区别？
82.tcp 为什么要三次握手，两次不行吗？为什么？
83.说一下 tcp 粘包是怎么产生的？
84.OSI 的七层模型都有哪些？
85.get 和 post 请求有哪些区别？
86.如何实现跨域？
87.说一下 JSONP 实现原理？


九、设计模式
88.说一下你熟悉的设计模式？
89.简单工厂和抽象工厂有什么区别？


十、Spring/Spring MVC
90.为什么要使用 spring？
91.解释一下什么是 aop？
92.解释一下什么是 ioc？
93.spring 有哪些主要模块？
94.spring 常用的注入方式有哪些？
95.spring 中的 bean 是线程安全的吗？
96.spring 支持几种 bean 的作用域？
97.spring 自动装配 bean 有哪些方式？
98.spring 事务实现方式有哪些？
99.说一下 spring 的事务隔离？
100.说一下 spring mvc 运行流程？
101.spring mvc 有哪些组件？
102.@RequestMapping 的作用是什么？
103.@Autowired 的作用是什么？


十一、Spring Boot/Spring Cloud
104.什么是 spring boot？
105.为什么要用 spring boot？
106.spring boot 核心配置文件是什么？
107.spring boot 配置文件有哪几种类型？它们有什么区别？
108.spring boot 有哪些方式可以实现热部署？
109.jpa 和 hibernate 有什么区别？
110.什么是 spring cloud？
111.spring cloud 断路器的作用是什么？
112.spring cloud 的核心组件有哪些？


十二、Hibernate
113.为什么要使用 hibernate？
114.什么是 ORM 框架？
115.hibernate 中如何在控制台查看打印的 sql 语句？
116.hibernate 有几种查询方式？
117.hibernate 实体类可以被定义为 final 吗？
118.在 hibernate 中使用 Integer 和 int 做映射有什么区别？
119.hibernate 是如何工作的？
120.get()和 load()的区别？
121.说一下 hibernate 的缓存机制？
122.hibernate 对象有哪些状态？
123.在 hibernate 中 getCurrentSession 和 openSession 的区别是什么？
124.hibernate 实体类必须要有无参构造函数吗？为什么？


十三、Mybatis
125.mybatis 中 #{}和 ${}的区别是什么？
126.mybatis 有几种分页方式？
127.RowBounds 是一次性查询全部结果吗？为什么？
128.mybatis 逻辑分页和物理分页的区别是什么？
129.mybatis 是否支持延迟加载？延迟加载的原理是什么？
130.说一下 mybatis 的一级缓存和二级缓存？
131.mybatis 和 hibernate 的区别有哪些？
132.mybatis 有哪些执行器（Executor）？
133.mybatis 分页插件的实现原理是什么？
134.mybatis 如何编写一个自定义插件？
十四、RabbitMQ
135.rabbitmq 的使用场景有哪些？
136.rabbitmq 有哪些重要的角色？
137.rabbitmq 有哪些重要的组件？
138.rabbitmq 中 vhost 的作用是什么？
139.rabbitmq 的消息是怎么发送的？
140.rabbitmq 怎么保证消息的稳定性？
141.rabbitmq 怎么避免消息丢失？
142.要保证消息持久化成功的条件有哪些？
143.rabbitmq 持久化有什么缺点？
144.rabbitmq 有几种广播类型？
145.rabbitmq 怎么实现延迟消息队列？
146.rabbitmq 集群有什么用？
147.rabbitmq 节点的类型有哪些？
148.rabbitmq 集群搭建需要注意哪些问题？
149.rabbitmq 每个节点是其他节点的完整拷贝吗？为什么？
150.rabbitmq 集群中唯一一个磁盘节点崩溃了会发生什么情况？
151.rabbitmq 对集群节点停止顺序有要求吗？
十五、Kafka
152.kafka 可以脱离 zookeeper 单独使用吗？为什么？
153.kafka 有几种数据保留的策略？
154.kafka 同时设置了 7 天和 10G 清除数据，到第五天的时候消息达到了 10G，这个时候 kafka 将如何处理？
155.什么情况会导致 kafka 运行变慢？
156.使用 kafka 集群需要注意什么？
十六、Zookeeper
157.zookeeper 是什么？
158.zookeeper 都有哪些功能？
159.zookeeper 有几种部署模式？
160.zookeeper 怎么保证主从节点的状态同步？
161.集群中为什么要有主节点？
162.集群中有 3 台服务器，其中一个节点宕机，这个时候 zookeeper 还可以使用吗？
163.说一下 zookeeper 的通知机制？
十七、MySql
164.数据库的三范式是什么？
165.一张自增表里面总共有 7 条数据，删除了最后 2 条数据，重启 mysql 数据库，又插入了一条数据，此时 id 是几？
166.如何获取当前数据库版本？
167.说一下 ACID 是什么？
168.char 和 varchar 的区别是什么？
169.float 和 double 的区别是什么？
170.mysql 的内连接、左连接、右连接有什么区别？
171.mysql 索引是怎么实现的？
172.怎么验证 mysql 的索引是否满足需求？
173.说一下数据库的事务隔离？
174.说一下 mysql 常用的引擎？
175.说一下 mysql 的行锁和表锁？
176.说一下乐观锁和悲观锁？
177.mysql 问题排查都有哪些手段？
178.如何做 mysql 的性能优化？
十八、Redis
179.redis 是什么？都有哪些使用场景？
180.redis 有哪些功能？
181.redis 和 memecache 有什么区别？
182.redis 为什么是单线程的？
183.什么是缓存穿透？怎么解决？
184.redis 支持的数据类型有哪些？
185.redis 支持的 java 客户端都有哪些？
186.jedis 和 redisson 有哪些区别？
187.怎么保证缓存和数据库数据的一致性？
188.redis 持久化有几种方式？
189.redis 怎么实现分布式锁？
190.redis 分布式锁有什么缺陷？
191.redis 如何做内存优化？
192.redis 淘汰策略有哪些？
193.redis 常见的性能问题有哪些？该如何解决？
十九、JVM
194.说一下 jvm 的主要组成部分？及其作用？
195.说一下 jvm 运行时数据区？
196.说一下堆栈的区别？
197.队列和栈是什么？有什么区别？
198.什么是双亲委派模型？
199.说一下类加载的执行过程？
200.怎么判断对象是否可以被回收？
201.java 中都有哪些引用类型？
202.说一下 jvm 有哪些垃圾回收算法？
203.说一下 jvm 有哪些垃圾回收器？
204.详细介绍一下 CMS 垃圾回收器？
205.新生代垃圾回收器和老生代垃圾回收器都有哪些？有什么区别？
206.简述分代垃圾回收器是怎么工作的？
207.说一下 jvm 调优的工具？
208.常用的 jvm 调优的参数都有哪些？

