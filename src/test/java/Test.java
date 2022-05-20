import anshay.notebook.common.bo.ListNode;
import anshay.notebook.common.bo.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author machao
 * @date 2022/5/19
 */
public class Test {
	public int finalValueAfterOperations(String[] operations) {
		int res = 0;
		for (String str : operations) {
			res += (44 - (int) (str.charAt(1)));
		}
		return res;
	}

	public String reverseLeftWords(String s, int n) {
		return s.substring(n) + s.substring(0, n);
	}

	public int[] runningSum(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			nums[i] = sum;
		}
		return nums;
	}

	public int[] countPoints(int[][] points, int[][] queries) {
		int[] res = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			for (int[] point : points) {
				res[i] += (point[0] - queries[i][0]) * (point[0] - queries[i][0])
						+ (point[1] - queries[i][1]) * (point[1] - queries[i][1])
						> (queries[i][2]) * (queries[i][2]) ? 1 : 0;
			}

		}
		return res;
	}

	public int divide(int dividend, int divisor) {
		int a = dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0 ? 1 : -1;
		return a * (Math.abs(dividend / divisor));
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return false;
		} else if (p == null) {
			return true;
		}
		return p.val != q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	public static void main(String[] args) {
		boolean b = true ^ false;
		int a = 0;
	}

	public int minPartitions(String n) {
		int max = 0;
		for (char c : n.toCharArray()) {
			max = Math.max(max, c - '0');
		}
		return max;
	}

	public int mostWordsFound(String[] sentences) {
		int max = 0;
		for (String sentence : sentences) {
			max = Math.max(max, sentence.split(" ").length);
		}
		return max;
	}

	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}

	public int xorOperation(int n, int start) {
		int temp = start;
		for (int i = 1; i < n; i++) {
			temp = start ^ (start + 2 * i);
		}
		return temp;
	}

	public int minimumSum(int num) {
		int[] array = new int[4];
		for (int i = 0; i < array.length && num > 0; i++) {
			array[i] = num % 10;
			num /= 10;
		}
		Arrays.sort(array);
		return (array[0] + array[1]) * 10 + array[2] + array[3];
	}

	public int countKDifference(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int count = 0;
		for (int num : nums) {
			count += map.getOrDefault(num + k, 0);
			count += map.getOrDefault(num - k, 0);
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		return count;
	}

	public int trap(int[] height) {
		int l = 0, r = height.length - 1;
		int lMax = 0, rMax = 0;
		int count = 0;
		while (l < r) {
			if (height[l] < height[r]) {
				if (height[l] > lMax) {
					// 更新边界
					lMax = height[l];
				} else {
					// 增加
					count += lMax - height[l];
				}
				l++;
			} else {
				if (height[r] > rMax) {
					rMax = height[r];
				} else {
					count += rMax - height[r];
				}
				r--;
			}
		}
		return count;
	}
}
