package anshay.notebook.leetcode;

/**
 * 896. 单调数列
 * <p>
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * <p>
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * <p>
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 *
 * @author machao
 * @date 2021/2/28
 */
public class Solution896 {
    public boolean isMonotonic(int[] A) {
        boolean up = true;
        boolean down = true;
        for (int i = 1; i < A.length && (up || down); i++) {
            if (down && A[i] < A[i - 1]) {
                down = false;
            } else if (up && A[i] > A[i - 1]) {
                up = false;
            }
        }
        return up || down;
    }

}
