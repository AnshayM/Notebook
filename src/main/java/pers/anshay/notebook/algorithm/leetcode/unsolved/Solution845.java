package pers.anshay.notebook.algorithm.leetcode.unsolved;

/**
 * 845. 数组中的最长山脉
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * <p>
 * B.length >= 3
 * 存在 0 < i< B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * <p>
 * 给出一个整数数组 A，返回最长 “山脉”的长度。
 * <p>
 * 如果不含有 “山脉”则返回 0。
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 * 示例 2：
 * <p>
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 *
 * <p>
 * 提示：
 * <p>
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * <p>
 *
 * @author machao
 * @date 2020/10/26
 */
public class Solution845 {
    public static void main(String[] args) {
        int[] a = new int[]{2, 1, 4, 7, 3, 2, 5};
        int i = longestMountain(a);
        int b = 0;
    }

    //思路，计算每个元素靠左有多少个，靠右有多少个，然后在计算和，取最大。
    public static int longestMountain(int[] A) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }
        int[] left = new int[n];
        for (int i = 1; i < n; ++i) {
            left[i] = A[i - 1] < A[i] ? left[i - 1] + 1 : 0;
        }
        int[] right = new int[n];
        for (int i = n - 2; i > 0; --i) {
            right[i] = A[i + 1] < A[i] ? right[i + 1] + 1 : 0;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            //需要算上自己
            ans = Math.max(ans, left[i] + right[i] + 1);
        }
        return ans;
    }

}
