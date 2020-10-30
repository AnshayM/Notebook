package anshay.notebook.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 整数反转
 * <p>
 * 要考虑整数越界问题，使用long最后转int。Integer.MAX_VALUE是最大数字。
 *
 * @author: Anshay
 * @date: 2019/4/17
 */
public class Solution7 {
    public static void main(String[] args) {
        int a = reverse1(123);
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

    /**
     * 此方法有问题，会造成越界
     *
     * @param x
     * @return
     */
    public static int reverse1(int x) {
        if (x < Math.pow(-2, 31) || x > Math.pow(2, 31) - 1) {
            return 0;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        int isPositive = 1;
        int result = 0;
        if (x <= 0) {
            isPositive = -1;
            x = -x;
        }
        while (x > 0) {
            queue.offer(x % 10);
            x = x / 10;
        }

        while (!queue.isEmpty()) {
            int temp = queue.poll();
            result = result * 10 + temp;
        }
        result = result * isPositive;
        if (Math.pow(-2, 31) <= result && result <= Math.pow(2, 31) - 1) {
            return result;
        }
        return 0;
    }
}
