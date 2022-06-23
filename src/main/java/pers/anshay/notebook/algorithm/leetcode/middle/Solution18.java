package pers.anshay.notebook.algorithm.leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 *
 * @author machao
 * @date 2022/5/22
 */
public class Solution18 {
	public static void main(String[] args) {
		int[] ints = {1, -2, -5, -4, -3, 3, 3, 5};
		List<List<Integer>> lists = fourSum(ints, -11);
		System.out.println(lists);
	}

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length < 4) {
			return res;
		}
		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 3; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			threeSum(nums, i, target - nums[i], res);
		}
		return res;
	}

	public static void threeSum(int[] nums, int first, int target, List<List<Integer>> res) {
		if (nums.length - first < 4) {
			return;
		}
		for (int i = first + 1; i < nums.length - 2; i++) {
			if (i > first + 1 && nums[i] == nums[i - 1]) {
				continue;
			}
			int l = i + 1, r = nums.length - 1;
			while (l < r) {
				int sum = nums[i] + nums[l] + nums[r];
				if (sum == target) {
					res.add(Arrays.asList(nums[first], nums[i], nums[l], nums[r]));
					while (l < r && nums[l] == nums[l + 1]) l++;
					while (l < r && nums[r] == nums[r - 1]) r--;
					l++;
					r--;
				} else if (sum < target) {
					l++;
				} else {
					r--;
				}
			}
		}
	}

}
