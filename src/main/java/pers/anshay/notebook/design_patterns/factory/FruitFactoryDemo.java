package pers.anshay.notebook.design_patterns.factory;

import org.apache.commons.lang3.StringUtils;


/**
 * 工厂模式 水果
 *
 * @author anshay
 * @date 2022/12/10
 */
class FruitFactoryDemo {
    public static void main(String[] args) {
        FruitFactory factory = new FruitFactory();
        Fruit apple = factory.produce(FruitFactory.APPLE);
        apple.print();
        Fruit orange = factory.produce(FruitFactory.ORANGE);
        orange.print();
        Fruit unknown = factory.produce("unknown");
        unknown.print();
    }
}

class FruitFactory {
    public static final String APPLE = "apple";
    public static final String ORANGE = "orange";

    public Fruit produce(String type) {
        if (StringUtils.equals(APPLE, type)) {
            return new Apple();
        } else if (StringUtils.equals(ORANGE, type)) {
            return new Orange();
        } else {
            System.out.println("please check the value of type!");
            return null;
        }
    }
}

interface Fruit {

    /**
     * 实现方法
     */
    void print();
}

/*实现类*/
class Apple implements Fruit {
    @Override
    public void print() {
        System.out.println("this is an apple!");
    }
}

class Orange implements Fruit {
    @Override
    public void print() {
        System.out.println("this is an orange!");
    }
}




