package array;

/**
 * 二维数组，对角线遍历
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 * <p>
 * 注意：数组的第一个参数是纵坐标
 * <p>
 * 思路：m+n为偶数时上右方向，为奇数时下左方向
 *
 * @author: Anshay
 * @date: 2019/4/12
 */
public class Solution4 {
    //未通过,数组越界（未考虑部分边界条件）
    public static void main(String[] args) {
        int[][] n = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] x = findDiagonalOrder(n);
        System.out.println(x);
    }

    public static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[]{};
        }
        int r = 0;//行
        int c = 0;//列
        int row = matrix.length;//行数
        int col = matrix[0].length;//列数
        int res[] = new int[row * col];
        for (int i = 0; i < res.length; i++) {
            res[i] = matrix[r][c];//记录值
            if ((r + c) % 2 == 0) {//如果是偶数
                if (c == col - 1) {
                    //如果是最后一列，行加加
                    r++;
                } else if (r == 0) {
                    //如果是第一行，列加加
                    c++;
                } else {
                    //向右上移动
                    r--;
                    c++;
                }
            } else {
                //如果是奇数
                if (r == row - 1) {
                    //如果是最后一行列加加
                    c++;
                } else if (c == 0) {
                    //如果列为行加加
                    r++;
                } else {
                    //向左下移动
                    r++;
                    c--;
                }
            }
        }
        return res;
    }
}
