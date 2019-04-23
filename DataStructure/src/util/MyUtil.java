package util;

import java.util.logging.Logger;

/**
 * 工具类
 *
 * @author: Anshay
 * @date: 2019/4/22
 */
public class MyUtil {
    /*Log*/
    public static void print(String str, Object o) {
        System.out.print(str + " ");
        System.out.println(o);
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
