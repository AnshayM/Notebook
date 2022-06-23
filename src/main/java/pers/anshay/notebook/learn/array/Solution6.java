package pers.anshay.notebook.learn.array;

/**
 * 二进制求和
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * 输入为非空字符串且只包含数字 1 和 0
 * <p>
 * 新建一个数组，长度为a和b最长的加1，然后进行遍历
 *
 * @author: Anshay
 * @date: 2019/5/14
 */
public class Solution6 {
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carrier = 0;
        StringBuffer sb = new StringBuffer();
        while (i >= 0 || j >= 0) {
            int ia = i >= 0 ? a.charAt(i--) - '0' : 0;
            int ib = j >= 0 ? b.charAt(j--) - '0' : 0;
            int r = ia + ib + carrier;
            carrier = r / 2;
            r %= 2;
            sb.insert(0, "" + r);
        }
        if (carrier == 1) {
            sb.insert(0, "1");
        }
        return sb.toString();
    }

    public String addBinary1(String a, String b) {
        if (a.length() < b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
        int len1 = a.length();
        int len2 = b.length();
        int[] res = new int[len1];
        // 是否要进位
        boolean flag = false;
        for (int i = 0; i < len2; i++) {
            int item = (a.charAt(len1 - 1 - i) - '0') + (b.charAt(len2 - 1 - i) - '0');
            if (flag) {
                flag = false;
                item++;
            }
            flag = item > 1;
            res[len1 - 1 - i] = item % 2;
        }
        for (int i = len2; i < len1; i++) {
            int item = a.charAt(len1 - 1 - i) - '0';
            if (flag) {
                flag = false;
                item++;
            }
            flag = item > 1;
            res[len1 - 1 - i] = item % 2;
        }
        StringBuilder sb = flag ? new StringBuilder("1") : new StringBuilder();
        for (int n : res) {
            sb.append(String.valueOf(n));
        }
        return sb.toString();
    }
}
