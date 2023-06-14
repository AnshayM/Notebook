package pers.anshay.notebook.design_patterns.buider;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 *
 * @author anshay
 * @date 2022/12/10
 */
public class BuilderPatternDemo {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " +vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " +nonVegMeal.getCost());
    }
}


/**
 * 食物条目和食物包装接口
 */
interface Item {
    String name();

    Packing packing();

    float price();
}

/*----------------------------------------打包方式--------------------------------------------*/

/**
 * 打包方式
 */
interface Packing {
    String pack();
}

/**
 * 纸包装
 */
class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}

/**
 * 瓶装
 */
class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}

/*----------------------------------------食物条目--------------------------------------------*/

abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }
}

abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }
}

class VegBurger extends Burger {

    @Override
    public String name() {
        return "VegBurger";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}

class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "ChickenBurger";
    }

    @Override
    public float price() {
        return 40.0f;
    }
}

class Coke extends ColdDrink {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 10.0f;
    }
}

class Pepsi extends ColdDrink {

    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 10.0f;
    }
}


/*----------------------------------------客户的餐--------------------------------------------*/

class Meal {
    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item) {
        items.add(item);
    }

    public float getCost() {
        float total = 0f;
        for (Item item : items) {
            total += item.price();
        }
        return total;
    }

    public void showItems(){
        for (Item item : items) {
            System.out.print("Item : "+item.name());
            System.out.print(", Packing : "+item.packing().pack());
            System.out.println(", Price : "+item.price());
        }
    }
}
/*----------------------------------------点餐实例-可以是固定的套餐--------------------------------------------*/

class MealBuilder{
    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}



