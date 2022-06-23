package pers.anshay.notebook.algorithm.leetcode;

/**
 * 斐波那契数
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * @author: Anshay
 * @date: 2019/4/29
 */
public class Solution509 {
    public static void main(String[] args) {
        int a = fib2(2);
    }

    public static int fib(int n) {
        int f1 = 0, f2 = 1;
        if (n == 0) {
            return f1;
        } else if (n == 1) {
            return f2;
        }
        for (int i = 2; i <= n; i++) {
            int temp = f2;
            f2 += f1;
            f1 = temp;
        }
        return f2;
    }

    /**
     * 这个方法不如循环有效率
     *
     * @param n
     * @return
     */
    public static int fib2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib2(n - 1) + fib2(n - 2);
    }
}
