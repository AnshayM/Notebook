package anshay.notebook.leetcode;

/**
 * 按奇偶排序数组
 * <p>
 * 给定一个非负整数数组 A，返回一个由 A 的所有偶数元素组成的数组，后面跟 A 的所有奇数元素。
 * <p>
 * 你可以返回满足此条件的任何数组作为答案。
 *
 * @author: Anshay
 * @date: 2019/4/23
 */
public class Solution905 {
    public int[] sortArrayByParity(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        int[] result = new int[A.length];
        int oddNum = A.length - 1;
        int evenNum = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                result[evenNum++] = A[i];
            } else {
                result[oddNum--] = A[i];
            }
        }
        return result;
    }
}
