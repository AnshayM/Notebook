package hashtable;

import util.MyUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 存在重复元素
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 * <p>
 * 要求：使用set查重
 *
 * @author: Anshay
 * @date: 2019/5/23
 */
public class Solution1 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 1, 222};
        MyUtil.print(containsDuplicate(nums));
    }

    public static boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return false;
        }
        Set set = new HashSet();
        for (int item : nums) {
            if (!set.add(item)) {
                return true;
            }
        }
        return false;
    }
}
