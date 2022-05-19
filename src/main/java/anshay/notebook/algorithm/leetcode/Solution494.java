package anshay.notebook.algorithm.leetcode;

/**
 * 目标和
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。
 * 现在你有两个符号 + 和 -。对于数组中的任意一个整数，
 * 你都可以从 + 或 -中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * @author: Anshay
 * @date: 2019/4/27
 */
public class Solution494 {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null)
            return 0;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (S > sum || (sum + S) % 2 == 1)
            return 0;
        sum = (sum + S) / 2;
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[sum];
    }
}
