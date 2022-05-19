package anshay.notebook.algorithm.leetcode.core;

/**
 * 42 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 双指针，但是维护最小边，走一步加一格
 *
 * @author machao
 * @date 2022/5/14
 */
public class Solution42 {
	public int trap(int[] height) {
		int l = 0, r = height.length - 1;
		int leftMax = 0, rightMax = 0;
		int ans = 0;
		while (l < r) {
			if (height[l] < height[r]) {
				if (height[l] > leftMax) {
					leftMax = height[l];
				} else {
					ans += leftMax - height[l];
				}
				l++;
			} else {
				if (height[r] > rightMax) {
					rightMax = height[r];
				} else {
					ans += rightMax - height[r];
				}
				r--;
			}
		}
		return ans;
	}
}
