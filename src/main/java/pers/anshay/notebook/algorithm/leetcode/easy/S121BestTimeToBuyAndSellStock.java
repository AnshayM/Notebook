package pers.anshay.notebook.algorithm.leetcode.easy;

/**
 * 买卖股票的最佳时机
 * 给定一个数组，它的第i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 注意你不能在买入股票前卖出股票
 * <p>
 * 找到最低价和最高价就行。
 *
 * @author: Anshay
 * @date: 2019/7/3
 */
public class S121BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (minPrice >= price) {
                //找到当天起以后的最低价
                minPrice = price;
            } else {
                // 当前价格减去当前阶段最低价即 当前阶段利润，比较当前阶段利润和历史最大利润，取大者
                if (price - minPrice > maxProfit) {
                    maxProfit = price - minPrice;
                }
            }
        }
        return maxProfit;
    }
}
