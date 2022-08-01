package pers.anshay.notebook.algorithm.leetcode.core;

/**
 * 删除排序数组中的重复项
 * 给定的是个排序数组
 *
 * @author: Anshay
 * @date: 2019/4/19
 */
public class Solution26 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1, 4, 5};
        int a = removeDuplicates(nums);
        System.out.print(a);
    }

    public static int removeDuplicates(int[] nums) {
        int number = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[number] != nums[i]) {
                // 找到第一个和指定数不同的数，让指定数的下一位为这个值
                nums[++number] = nums[i];
            }
        }
        //这里number是下标，前x个要对number自增1
        return ++number;
    }
}
