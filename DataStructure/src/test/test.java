package test;

import util.MyUtil;

import java.util.*;

/**
 * @author: Anshay
 * @date: 2019/4/19
 */
public class test {
    public static void main(String[] args) {
//        RandomizedSet1 obj = new RandomizedSet1();
//        boolean param_1 = obj.insert(1);
//        boolean param_2 = obj.remove(2);
//        int param_3 = obj.getRandom();
        int i = 0;
        while (i++ < 100) {
            int index = new Random().nextInt(9);
            MyUtil.print(index + "--");

        }

    }

}
