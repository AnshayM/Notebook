package pers.anshay.notebook.algorithm.leetcode.middle;

/**
 * 1052. 爱生气的书店老板
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 * <p>
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * <p>
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 * <p>
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 *
 * 这道题是动态规划，滑动窗口
 *
 * @author machao
 * @date 2021/2/23
 */
public class Solution1052 {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int length = customers.length;
        int sum1 = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                sum1 += customers[i];
            }
        }
        int increase = 0;
        //前X分钟额外满意数
        for (int i = 0; i < X; i++) {
            increase += customers[i] * grumpy[i];
        }
        //从第X+1开始往后，每个比较
        int maxIncrease = increase;
        for (int i = X; i < length; i++) {
            increase = increase - customers[i - X] * grumpy[i - X] + customers[i] * grumpy[i];
            maxIncrease = Math.max(maxIncrease, increase);
        }
        return sum1 + maxIncrease;

    }
}
