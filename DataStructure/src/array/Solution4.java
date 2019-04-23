package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 二维数组，对角线遍历
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 * <p>
 * 注意：数组的第一个参数是纵坐标
 *
 * @author: Anshay
 * @date: 2019/4/12
 */
public class Solution4 {

    public static void main(String[] args) {
        int[][] n = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] x = findDiagonalOrder(n);
        System.out.println(x);
    }

    public static int[] findDiagonalOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int clos = matrix.length;
        int rows = matrix[0].length;
        if (clos > 0 && rows > 0) {
            list.add(matrix[0][0]);
        }
        int i = 0;// 纵坐标
        int j = 0;// 横坐标
        boolean up = true;
        //保证坐标都在矩阵内
        while (list.size() < rows * clos) {
            // 是否右上
            if (up) {
                // 是否靠最顶
                if (i == 0) {
                    up = false;
                    j++;
                } else {
                    j++;
                    i--;
                }
            } else {
                // 是否靠近最左边
                if (j == 0) {
                    up = true;
                    i++;
                } else {
                    j--;
                    i++;
                }
            }
            if (i < clos && i >= 0 && j < rows && j >= 0) {
                list.add(matrix[i][j]);
            }
        }
        int[] nums = new int[list.size()];
        for (int index = 0; index < list.size(); index++) {
            nums[index] = list.get(index);
        }
        return nums;
    }
}
