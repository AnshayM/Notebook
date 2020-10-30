package anshay.notebook.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 思路：用hashMap，nums[i]做key，i做value
 *
 * @author: Anshay
 * @date: 2019/4/30
 */
public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            Integer j = map.get(target - nums[i]);
            /*避免元素是目标值一半的情况 */
            if (j != null && j != i) {
                return new int[]{i, j};
            }
        }
        return new int[]{};
    }
}
