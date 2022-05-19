package anshay.notebook.algorithm.leetcode;

/**
 * 153. 寻找旋转排序数组中的最小值
 *
 * @author machao
 * @date 2021/4/8
 */
public class Solution153 {
    /**
     * 一次循环
     */
    public int findMin1(int[] nums) {
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i - 1] > nums[i]) {
                return nums[i];
            }
        }
        return nums[0];
    }

    /**
     * 二分查找
     */
    public int findMin(int[] nums) {
        int low = 0, high = nums.length-1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                low = pivot + 1;
            }
        }
        return nums[low];
    }
}
