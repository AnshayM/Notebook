package anshay.notebook.leetcode;

import java.util.ArrayList;
import java.util.List;

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
                number++;
                nums[number] = nums[i];
            }
        }
        number++;
        return number;
    }

    /**
     * 这种方式，时间和空间消耗都很大
     */
    public int removeDuplicates1(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        return list.size();
    }
}
