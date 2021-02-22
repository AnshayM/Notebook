package anshay.notebook.leetcode;

/**
 * 766. 托普利茨矩阵
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 * <p>
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 * <p>
 *
 * @author machao
 * @date 2021/2/22
 */
public class Solution766 {
    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}};
        boolean toeplitzMatrix = isToeplitzMatrix1(a);
    }

    public static boolean isToeplitzMatrix1(int[][] matrix) {
        if (matrix == null) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int addNum = m * n;
        if (m == 1 || n == 1) {
            return true;
        }
        int[] valueArray = new int[m * n * 2];
        //设置默认值为-1，与最小值区分
        for (int i = valueArray.length - 1; i >= 0; i--) {
            valueArray[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int value = matrix[i][j];
                int index_1 = i - j + addNum;
                //判断是否被初始化
                if (valueArray[index_1] > -1 && value != valueArray[index_1]) {
                    return false;
                } else {
                    valueArray[index_1] = value;
                }
            }
        }
        return true;
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

}
