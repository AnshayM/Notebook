package hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 存在重复元素 II
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k
 * <p>
 * 即只要有一个满足小于k就可以了
 *
 * @author: Anshay
 * @date: 2019/5/24
 */
public class Solution9 {

    /*在长度为k的子数组内判断有没有重复，只要有重复就返回true*/
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 2 || nums.length >= 35000) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        /*长度小于k+1时只要有重复就满足条件*/
        if (nums.length <= k + 1) {
            for (int item : nums) {
                if (!set.add(item)) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < nums.length - k; i++) {
                set.clear();
                for (int j = i; j < i + k; j++) {
                    if (!set.add(nums[j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int diff = i - map.get(nums[i]);
                if (diff <= k) {
                    return true;
                }
            }
            /*初始或者更新nums[i]的下标*/
            map.put(nums[i], i);
        }
        return false;
    }
}
