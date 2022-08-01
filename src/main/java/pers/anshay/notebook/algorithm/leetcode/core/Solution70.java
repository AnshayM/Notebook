package pers.anshay.notebook.algorithm.leetcode.core;

/**
 * 70.爬楼梯
 * 假设你正在爬楼梯。需要 n阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * <p>
 * 思路：第N级=第N-1级+第N-2级，但是直接递归重复项太多，会造成超时
 * 所以为了节省资源，这里采用正向循环方式。(但是和斐波拉数列起始数不同)
 *
 * @author: Anshay
 * @date: 2019/10/24
 */
public class Solution70 {
    public static void main(String[] args) {
        int res = climbStairs(4);

    }

    /**
     * f(x)=f(x-1)+f(x-2)
     * 0到0只有一种方案：f(0)=1,0到1级也只有一种方案f(1)=1
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        int f1 = 0, f2 = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            f1 = f2;
            f2 = r;
            r = f1 + f2;
        }
        return r;
    }


    public static int climbStairs2(int n) {
        /*1级1种，2级别2种，从这里开始累加*/
        if (n <= 2) {
            return n;
        }
        int f1 = 1;
        int f2 = 2;
        for (int i = 3; i <= n; i++) {
            int temp = f2;
            f2 = f1 + f2;
            f1 = temp;
        }
        return f2;

    }
}
