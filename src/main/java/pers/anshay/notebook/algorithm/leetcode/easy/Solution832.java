package pers.anshay.notebook.algorithm.leetcode.easy;

/**
 * 832. 翻转图像
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 * <p>
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 * <p>
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 * <p>
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 *
 * @author machao
 * @date 2021/2/24
 */
public class Solution832 {
    public int[][] flipAndInvertImage(int[][] A) {
        int length = A[0].length;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < (length-1)/2; j++) {
                int temp = A[i][j];
                A[i][j] = A[i][length-1-j] ^ 1;
                A[i][length - 1 - j] = temp ^ 1;
            }
        }
        return A;
    }
}
