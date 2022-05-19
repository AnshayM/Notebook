package anshay.notebook.algorithm.leetcode.core;

/**
 * 11 盛最多水的容器
 * 使用双指针来做,从两端逐步向内
 *
 * @author machao
 * @date 2022/5/12
 */
public class Solution11 {
	public int maxArea(int[] height) {
		int max = 0;
		int l = 0, r = height.length - 1;
		while (l < r) {
			int area = Math.min(height[l], height[r]) * (r - l);
			max = Math.max(max, area);
			if (height[l] < height[r]) {
				// 表示左边低，这时移动右边的只会比当前的area小，所以移动左边。
				l++;
			} else {
				r--;
			}

		}
		return max;
	}
}
