package pers.anshay.notebook.algorithm.leetcode;

/**
 * 将数组分成和相等的三个部分
 * <p>
 * 先求和sum，然后两边遍历求和等于sum/3
 * 数据小的时候单边遍历就行
 *
 * @author: Anshay
 * @date: 2019/4/23
 */
public class Solution1013 {
    public static void main(String[] args) {
        boolean a = canThreePartsEqualSum(null);
    }
    public static boolean canThreePartsEqualSum(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }
        int sum = 0;
        int cur = 0;
        for (int item : A) {
            sum += item;
        }
        if (sum % 3 != 0) {
            return false;
        }
        for (int i = 0; i < A.length; i++) {
            cur += A[i];
            if (cur == sum / 3) {
                cur = 0;
            }
        }
        return cur == 0;

    }
}
