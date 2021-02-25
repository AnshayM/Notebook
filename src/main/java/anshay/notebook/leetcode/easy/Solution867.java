package anshay.notebook.leetcode.easy;

/**
 * 867. 转置矩阵
 * 注意点：长短会不同，需要注意
 * @author machao
 * @date 2021/2/25
 */
public class Solution867 {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }
}
