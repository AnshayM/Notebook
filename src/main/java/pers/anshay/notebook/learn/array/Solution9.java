package pers.anshay.notebook.learn.array;


import pers.anshay.notebook.common.util.MyUtil;

/**
 * 最大连续1的个数
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 *
 * @author: Anshay
 * @date: 2019/5/14
 */
public class Solution9 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 0, 1, 1, 1};
        int res = findMaxConsecutiveOnes(nums);
        MyUtil.print(res);
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                res = res >= count ? res : count;
                count = 0;
            }
        }
        return Math.max(res, count);
    }
}
