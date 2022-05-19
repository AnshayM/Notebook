package anshay.notebook.algorithm.leetcode;

/**
 * 非递减数列
 * <p>
 * 给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * <p>
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 anshay.array[i] <= anshay.array[i + 1]。
 *
 * @author: Anshay
 * @date: 2019/4/23
 */
public class Solution665 {
    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return true;
        }
        int count = 0;
        if (nums[0] > nums[1]) {
            nums[0] = nums[1];
            count++;
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                count++;
                if (count > 1) {
                    return false;
                }
                if (nums[i - 1] > nums[i + 1]) {
                    nums[i + 1] = nums[i];
                } else {
                    nums[i] = nums[i - 1];
                }
            }
        }
        return true;
    }
}
