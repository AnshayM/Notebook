package pers.anshay.notebook.algorithm.leetcode;

/**
 * 74. 搜索二维矩阵
 * 递增的二维数组，判断是否寸在对应值
 *
 * @author machao
 * @date 2021/3/30
 */
public class Solution74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int middle = (high - low) / 2 + low;
            int val = matrix[middle / n][middle % n];
            if (target > val) {
                low = middle + 1;
            } else if (target < val) {
                high = middle - 1;
            } else {
                return true;
            }
        }

        return false;
    }

}
