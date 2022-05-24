package anshay.notebook.algorithm.leetcode.core;

/**
 * 300. 最长递增子序列
 *
 * @author machao
 * @date 2022/5/24
 */
public class TSolution300 {
	public int lengthOfLIS(int[] nums) {
		// 动态规划
		// dp[n]=max(dp[j])+1,0<=j<i,and nums[j]<nums[i]
		// 考虑i前面的所有数字
		if (nums.length==0) {
			return 0;
		}
		int[] dp = new int[nums.length];
		dp[0] = 1;
		int max = 1;
		for (int i = 1; i < nums.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		return max;

	}
}
