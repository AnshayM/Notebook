package pers.anshay.notebook.algorithm.leetcode.old;

/**
 * 山脉数组的峰顶索引
 * 我们把符合下列属性的数组 A 称作山脉：
 * A.length >= 3
 * 存在 0 < i < A.length - 1 使得A[0] < A[1]  < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * 给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。
 * <p>
 * <p>
 * 其实就是找最大值的下标
 *
 * @author: Anshay
 * @date: 2019/4/23
 */
public class Solution852 {
    public int peakIndexInMountainArray(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            max = A[max] >= A[i] ? max : i;
        }
        return max;
    }
}
