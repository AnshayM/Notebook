package anshay.notebook.algorithm.leetcode;

/**
 * 303,前缀和
 * 给定一个整数数组 nums，求出数组从索引i到j（i≤j）范围内元素的总和，包含i、j两点。
 * <p>
 * 实现 NumArray 类：
 * <p>
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 从索引i到j（i≤j）范围内元素的总和，包含i、j两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 *
 * @author machao
 * @date 2021/3/1
 */
public class Solution303 {
    class NumArray {
        int[] sums;

        public NumArray(int[] nums) {
            //多一个是为了避免处理i=0的时候
            sums = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sums[i + 1] = nums[i] + sums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sums[j + 1] - sums[i];
        }
    }
}

