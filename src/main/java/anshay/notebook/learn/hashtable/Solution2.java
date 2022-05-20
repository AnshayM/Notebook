package anshay.notebook.learn.hashtable;

import java.util.*;

/**
 * 只出现一次的数字
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 思路：如果用hashSet做，发现是重复的时候就将其删除，因为是两个，所以不会再有插入的情况。最后一个剩余的就是单独的
 *
 * @author: Anshay
 * @date: 2019/5/23
 */
public class Solution2 {
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            /*^表示位异或，相同为0，不同为1。交换律可知每一位最后都只剩下单独的那一个的值*/
            res ^= nums[i];
        }
        return res;
    }

    /*使用HashSet，使用了额外的空间，时间也慢*/
    public int singleNumber1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Set<Integer> set = new HashSet();
        for (int item : nums) {
            if (!set.add(item)) {
                set.remove(item);
            }
        }
        if (set.size() == 1) {
            for (Integer item : set) {
                return item;
            }
        }
        return -1;
    }
}
