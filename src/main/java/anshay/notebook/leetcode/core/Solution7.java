package anshay.notebook.leetcode.core;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 整数反转
 * <p>
 * 要考虑整数越界问题，使用long最后转int。Integer.MAX_VALUE是最大数字。
 * 先取符号,用long存取结果
 *
 * @author: Anshay
 * @date: 2019/4/17
 */
public class Solution7 {
    public static void main(String[] args) {
        int a = reverse(123);
        System.out.print(a);
    }

    public static int reverse(int x) {
        long result = 0;
        int temp = Math.abs(x);
        while (temp > 0) {
            result = result * 10 + temp % 10;
            temp /= 10;
        }
        if (result <= Integer.MAX_VALUE) {
            return (int) (x > 0 ? result : -result);
        }
        return 0;
    }
}
