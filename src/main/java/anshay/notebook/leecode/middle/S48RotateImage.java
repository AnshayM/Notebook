package anshay.notebook.leecode.middle;

import anshay.notebook.util.MyUtil;

/**
 * 旋转图像
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * 思路：上下翻转，然后按右下对角线交换。
 *
 * @author: Anshay
 * @date: 2019/7/3
 */
public class S48RotateImage {
    public static void main(String[] args) {
        int[][] n = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(n);
        MyUtil.print(12);
    }

    public static void rotate(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int n = matrix.length;
        int start = 0;
        int end = n - 1 - start;
        /*上下交换*/
        while (start < end) {
            int[] temp = matrix[start];
            matrix[start++] = matrix[end];
            matrix[end--] = temp;
        }
        /*对角线交换*/
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /*一遍完成的方法,时间复杂度低一倍*/
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        int l = 0;
        while (n - 2 * l >= 0 && n - 2 * l != 1) {
            int temp;
            for (int i = l; i < n - l - 1; i++) {
                temp = matrix[l][i];
                matrix[l][i] = matrix[n - 1 - i][l];
                matrix[n - 1 - i][l] = matrix[n - 1 - l][n - 1 - i];
                matrix[n - 1 - l][n - 1 - i] = matrix[i][n - 1 - l];
                matrix[i][n - 1 - l] = temp;
            }
            l++;
        }
    }
}
