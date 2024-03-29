package pers.anshay.notebook.algorithm.leetcode.old;

/**
 * 保持城市天际线
 * 在二维数组grid中，grid[i][j]代表位于某处的建筑物的高度。 我们被允许增加任何数量（不同建筑物的数量可能不同）的建筑物的高度。 高度 0 也被认为是建筑物。
 * 最后，从新数组的所有四个方向（即顶部，底部，左侧和右侧）观看的“天际线”必须与原始数组的天际线相同。 城市的天际线是从远处观看时，由所有建筑物形成的矩形的外部轮廓。 请看下面的例子。
 * 建筑物高度可以增加的最大总和是多少？
 * <p>
 * 思路：计算每行每列的行、列最大值吗，取其中较小的。
 *
 * @author: Anshay
 * @date: 2019/5/3
 */
public class Solution807 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int row = grid.length;
        int clo = grid[0].length;
        int rowMax[] = new int[row];
        int cloMax[] = new int[clo];
        for (int i = 0; i < row; i++) {
            int maxTemp = grid[i][0];
            for (int j = 0; j < clo; j++) {
                if (grid[i][j] > maxTemp) {
                    maxTemp = grid[i][j];
                }
            }
            rowMax[i] = maxTemp;
        }
        for (int j = 0; j < clo; j++) {
            int maxTemp = grid[0][j];
            for (int i = 0; i < row; i++) {
                if (grid[i][j] > maxTemp) {
                    maxTemp = grid[i][j];
                }
            }
            cloMax[j] = maxTemp;
        }
        int sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < clo; j++) {
                sum += Math.min(rowMax[i], cloMax[j]) - grid[i][j];
            }
        }
        return sum;
    }
}
