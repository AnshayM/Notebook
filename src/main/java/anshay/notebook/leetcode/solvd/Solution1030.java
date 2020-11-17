package anshay.notebook.leetcode.solvd;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1030. 距离顺序排列矩阵单元格
 * 直接存点，然后返回
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * <p>
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * <p>
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 *
 * @author machao
 * @date 2020/11/17
 */
public class Solution1030 {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                res[i * C + j] = new int[]{i, j};
            }
        }
        Arrays.sort(res, Comparator.comparing(v -> Math.abs(v[0] - r0) + Math.abs(v[1] - c0)));
        return res;
    }
}
