package pers.anshay.notebook.algorithm.leetcode.old;

/**
 * 1365. 有多少小于当前数字的数字
 * 给你一个数组nums，对于其中每个元素nums[i]，请你统计数组中比它小的所有数字的数目。
 * <p>
 * 换而言之，对于每个nums[i]你必须计算出有效的j的数量，其中 j 满足j != i 且 nums[j] < nums[i]。
 * <p>
 * 以数组形式返回答案。
 *
 * @author machao
 * @date 2020/10/26
 * 提示：
 * <p>
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 */
public class Solution1365 {
    //方法1：暴力循环，时间复杂度是N^2
    //方法2：快排，然后对排序后的进行判断下标。
    //方法3：计数排序


    public int[] smallerNumbersThanCurrent(int[] nums) {
        int length = 100 + 1;//这个根据具体来定
        int n = nums.length;
        int[] count = new int[length];
        for (int i = 0; i < n; i++) {
            count[nums[i]]++;
        }

        for (int i = 1; i < length-1; i++) {
            count[i] += count[i - 1];
        }
        int[] res = new int[n];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i] == 0 ? 0 : count[nums[i] - 1];
        }
        return res;
    }
}
