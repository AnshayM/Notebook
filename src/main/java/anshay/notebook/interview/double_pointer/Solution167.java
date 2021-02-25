package anshay.notebook.interview.double_pointer;

import java.util.HashMap;
import java.util.Map;

/**
 * 167：有序数组的 Two Sum
 * 两数之和 II - 输入有序数组
 *
 * @author: Anshay
 * @date: 2019/5/14
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * <p>
 * <p>
 * 思路：两头并进
 */
public class Solution167 {
    public static void main(String[] args) {
        int[] t = new int[]{2, 7, 11, 15};
        int[] a = twoSum(t, 9);

    }

    /**
     * 直接两头并进就行
     */
    public static int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] > target) {
                j--;
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                return new int[]{i + 1, j + 1};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 使用hashMap的方法，太麻烦了
     */
    public static int[] twoSum1(int[] numbers, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }
        for (int i = 0; i < numbers.length / 2 + 1; i++) {
            Integer index = map.get(target - numbers[i]);
            if (index != null && index != i) {
                res[0] = (i <= index ? i : index) + 1;
                res[1] = (i <= index ? index : i) + 1;
                return res;
            }
        }
        return res;
    }
}
