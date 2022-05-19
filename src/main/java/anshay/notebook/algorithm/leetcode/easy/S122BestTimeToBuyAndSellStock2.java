package anshay.notebook.algorithm.leetcode.easy;

/**
 * 买卖股票的最佳时机 II
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 思路：取结果为正值的prices[i + 1] - prices[i]的累积和
 *
 * @author: Anshay
 * @date: 2019/7/1
 */
public class S122BestTimeToBuyAndSellStock2 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] <= prices[i+1]) {
                sum += prices[i + 1] - prices[i];
            }
        }
        return sum;
    }
}
