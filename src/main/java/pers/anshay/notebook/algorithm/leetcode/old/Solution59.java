package pers.anshay.notebook.algorithm.leetcode.old;

/**
 * 59. 螺旋矩阵 II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * @author machao
 * @date 2021/3/16
 */
public class Solution59 {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int num = 1, tar = n * n;
        while (num <= tar) {
            //l->r
            for (int i = l; i <= r; i++) res[t][i] = num++;
            t++;
            //t->b
            for (int i = t; i <= b; i++) res[i][r] = num++;
            r--;
            //r->l
            for (int i = r; i >= l; i--) res[b][i] = num++;
            b--;
            //b->t
            for (int i = b; i >= t; i--) res[i][l] = num++;
            l++;
        }
        return res;
    }
}
