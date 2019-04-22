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
        char a = str.charAt(++i);
        MyUtil.logger.info("sss");
        MyUtil.logger.warning("ddd");
        System.out.print(a);

    }
}
