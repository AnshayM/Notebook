package test;

import util.MyUtil;

/**
 * @author: Anshay
 * @date: 2019/4/19
 */
public class test {
    public static void main(String[] args) {
        String str = "12345";
        int i = 0;
//        char a = str.charAt(i++);
        int j = i++;
//        MyUtil.print(a);
        MyUtil.print(j);
        MyUtil.print(i);

    }
}
