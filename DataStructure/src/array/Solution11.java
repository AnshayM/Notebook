package array;

/**
 * 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 思路：双索引，滑动窗口
 * 如果要使用 O(n log n) 时间复杂度，需要了解二分搜索法
 *
 * @author: Anshay
 * @date: 2019/5/15
 */
public class Solution11 {
    public static void main(String[] args) {
        int a = minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});

    }

    public static int minSubArrayLen(int s, int[] nums) {
        int i = 0;
        int j = 0;
        int sum = 0;
        int minLength = nums.length + 1;
        while (j < nums.length) {
            if (sum < s) {
                sum += nums[j++];
            }
            while (sum >= s) {
                minLength = Math.min(minLength, j - i);
                sum -= nums[i++];
            }
        }
        return minLength > nums.length ? 0 : minLength;
    }
}
