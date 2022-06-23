package pers.anshay.notebook.algorithm.leetcode.solvd;

import java.util.HashSet;
import java.util.Set;

/**
 * 349. 两个数组的交集
 *
 * @author machao
 * @date 2020/11/2
 */
public class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        for (int i : nums2) {
            if (set.contains(i)) {
                result.add(i);
            }
        }
        int[] res = new int[result.size()];
        int i = 0;
        for (Integer integer : result) {
            res[i] = integer;
            i++;
        }
        return res;
    }
}
