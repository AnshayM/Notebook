package pers.anshay.notebook.algorithm.leetcode.old;

/**
 * 按奇偶排序数组 II
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 * @author: Anshay
 * @date: 2019/4/28
 */
public class Solution922 {
    public int[] sortArrayByParityII(int[] A) {
        int oddNum = 1;
        int evenNum = 0;
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                res[evenNum] = A[i];
                evenNum += 2;
            } else {
                res[oddNum] = A[i];
                oddNum += 2;
            }
        }
        return res;

    }
}
