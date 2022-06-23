package pers.anshay.notebook.algorithm.leetcode.solvd;

/**
 * 941. 有效的山脉数组
 * 按题意模拟即可。我们从数组的最左侧开始向右扫描，直到找到第一个不满足 A[i] < A[i + 1]A[i]<A[i+1] 的下标 ii，
 * 那么 ii 就是这个数组的最高点的下标。如果 i = 0i=0 或者不存在这样的 ii（即整个数组都是单调递增的），
 * 那么就返回 \text{false}false。否则从 ii 开始继续向右扫描，判断接下来的的下标 jj 是否都满足 A[j] > A[j + 1]A[j]>A[j+1]，
 * 若都满足就返回 \text{true}true，否则返回 \text{false}false。
 *
 * @author machao
 * @date 2020/11/3
 */
public class Solution941 {

    public static boolean validMountainArray1(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }
        boolean left = true;
        if (A[0] >= A[1]) {
            return false;
        }
        for (int i = 1; i < A.length; i++) {
            int pre = A[i - 1], cur = A[i];
            if (pre == cur) {
                return false;
            }
            if (left) {
                if (pre > cur) {
                    left = false;
                }
            } else {
                if (pre < cur) {
                    return false;
                }
            }
        }
        return !left;
    }

    public boolean validMountainArray(int[] A) {
        int N = A.length;
        int i = 0;

        // 递增扫描
        while (i + 1 < N && A[i] < A[i + 1]) {
            i++;
        }

        // 最高点不能是数组的第一个位置或最后一个位置
        if (i == 0 || i == N - 1) {
            return false;
        }

        // 递减扫描
        while (i + 1 < N && A[i] > A[i + 1]) {
            i++;
        }

        return i == N - 1;
    }

}
