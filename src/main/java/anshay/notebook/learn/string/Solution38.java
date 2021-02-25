package anshay.notebook.learn.string;

import anshay.notebook.common.util.MyUtil;

/**
 * 报数
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * <p>
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * <p>
 * 第i+1个数是根据第i个数来的
 *
 * @author: Anshay
 * @date: 2019/7/10
 */
public class Solution38 {
    public static void main(String[] args) {
        String a = countAndSay(5);
        MyUtil.print(a);
    }

    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        /*获取上一个*/
        String temp = countAndSay(n - 1);
        StringBuilder ans = new StringBuilder("");
        for (int i = 0; i < temp.length(); ) {
            int j = i;
            while (j < temp.length() && temp.charAt(j) == temp.charAt(i)) {
                j++;
            }
            // 个数
            int k = j - i;
            ans.append(k + "" + temp.charAt(i));
            i = j;
        }
        return ans.toString();
    }
}
