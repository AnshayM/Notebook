package anshay.notebook.algorithm.leetcode;

/**
 * 分割等和子集
 *
 * @author machao
 * @date 2022/5/24
 */
public class Solution416 {
	public boolean canPartition(int[] nums) {
		// 01背包变体
		// 1.求出nums总和sum，奇数直接return false
		// 2.找满足和为sum的子集
		if (nums.length < 2) {
			return false;
		}
		int sum = 0, maxNum = 0;
		for (int num : nums) {
			sum += num;
			maxNum = Math.max(num, maxNum);
		}
		//必须是偶数才行
		if (sum % 2 > 0) {
			return false;
		}
		// 获取指定的值
		int target = sum / 2;
		if (maxNum > target) {
			return false;
		} else if (maxNum == target) {
			return true;
		}
		// 创建二维数组，行：物品索引，列：容量(包括0)
		boolean[][] dp = new boolean[nums.length][target + 1];

		// 补充表格，每行的第一个都可以装进去
		for (int i = 0; i < nums.length; i++) {
			dp[i][0] = true;
		}
		// 因为前面已经判断的都放的进去
		dp[0][nums[0]] = true;
		for (int i = 1; i < nums.length; i++) {
			for (int j = 1; j <= target; j++) {
				if (j >= nums[i]) {
					dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[nums.length - 1][target];
	}
}
