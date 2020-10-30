package anshay.notebook.leetcode;

/**
 * 斐波那契数
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * @author: Anshay
 * @date: 2019/4/29
 */
public class Solution509 {
    public static void main(String[] args) {
        int a = fib(2);
    }

    public static int fib(int N) {
        int f1 = 0;
        int f2 = 1;
        if (N == 0) {
            return f1;
        } else if (N == 1) {
            return f2;
        }
        for (int i = 2; i <= N; i++) {
            int temp = f2;
            f2 += f1;
            f1 = temp;
        }
        return f2;
    }
}
