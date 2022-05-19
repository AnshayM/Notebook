package anshay.notebook.algorithm.leetcode;

import java.util.TreeMap;

/**
 * 1438. 绝对差不超过限制的最长连续子数组
 * <p>
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 * <p>
 * 如果不存在满足条件的子数组，则返回 0 。
 *
 * @author machao
 * @date 2021/2/24
 */
public class Solution1438 {
    public int longestSubarray(int[] nums, int limit) {
        // 这里求差绝对值使用的红黑树，数组值为key，加进来次数为value，初始化时设0，加进来时value+1，
        // 判false时-1，为0时移除。
        TreeMap<Integer, Integer> m = new TreeMap<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < nums.length) {
            m.put(nums[right], m.getOrDefault(nums[right], 0) + 1);
            //不满足条件时，滑动窗口
            while (m.lastKey() - m.firstKey() > limit) {
                //已经判false的移出，因为右框在前必加进来，左框直接获取修改、移除即可
                m.put(nums[left], m.get(nums[left]) - 1);
                if (m.get(nums[left]) == 0) {
                    m.remove(nums[left]);
                }
                //窗口左端右滑动
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}
