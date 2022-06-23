package pers.anshay.notebook.algorithm.leetcode.solvd;

import java.util.Arrays;

/**
 * 973. 最接近原点的 K 个点
 *
 * @author machao
 * @date 2020/11/9
 */
public class Solution973 {
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, (v1, v2) -> v1[0] * v1[0] - v2[0] * v2[0] + v1[1] * v1[1] - v2[1] * v2[1]);
        return Arrays.copyOf(points, K);
    }
}
