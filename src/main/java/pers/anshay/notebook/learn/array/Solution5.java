package pers.anshay.notebook.learn.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * 定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 思路：在一个while循环里做四个方向的for循环，每个for循环中将当条直线上所有元素添加。然后修改下一个满足条件元素的坐标值。
 *
 * @author: Anshay
 * @date: 2019/5/14
 */
public class Solution5 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return list;
        }
        // 空余的x两端值，y的两端值
        int y1 = 0;
        int y2 = matrix.length - 1;
        int x1 = 0;
        int x2 = matrix[0].length - 1;

        while (x1 <= x2 && y1 <= y2) {
            for (int i = x1; i <= x2; i++) {
                list.add(matrix[y1][i]);
            }
            y1++;
            if (y1 > y2) {
                break;
            }
            for (int i = y1; i <= y2; i++) {
                list.add(matrix[i][x2]);
            }
            x2--;
            if (x1 > x2) {
                break;
            }
            for (int i = x2; i >= x1; i--) {
                list.add(matrix[y2][i]);
            }
            y2--;
            if (y1 > y2) {
                break;
            }
            for (int i = y2; i >= y1; i--) {
                list.add(matrix[i][x1]);
            }
            x1++;
        }
        return list;
    }
}
