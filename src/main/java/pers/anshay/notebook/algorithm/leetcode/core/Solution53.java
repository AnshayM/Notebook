package pers.anshay.notebook.algorithm.leetcode.core;

/**
 * 53. 最大子数组和
 * <p>
 * 这是一道动态规划的题目
 * 简单思路：遍历数组，然后计算每一步的sum，每一步取较大者更新sum，如果上一次的sum<=0,则直接取num比较即可。
 * 为负则放弃之前的数
 *
 * @author: Anshay
 * @date: 2019/10/24
 */
public class Solution53 {
    public int maxSubArray(int[] nums) {
        //用于存储最大的结果
        int max = nums[0];
        //用于存储当前最大的子序和
        int preSum = 0;
        for (int num : nums) {
            // 如果sum（现在代表左边的累计和）大于0，就累积。
            // 不然总是pre+num<=num成立，直接取num作为这一次循环值
            preSum = Math.max(preSum + num, num);
            //更新最大值
            max = Math.max(max, preSum);
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        int pre = 0, max = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            max = Math.max(max, pre);
        }
        return max;
    }

}
