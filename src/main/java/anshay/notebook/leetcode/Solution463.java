package anshay.notebook.leetcode;

/**
 * 463. 岛屿的周长
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地0 表示水域。
 * <p>
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * <p>
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * <p>
 *
 * <p>
 * <p>
 *
 * @author machao
 * @date 2020/10/30
 */
public class Solution463 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public int islandPerimeter2(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int ans = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (grid[i][j] != 1) {
                    continue;
                }
                for (int k = 0; k < 4; ++k) {
                    int tx = i + dx[k];
                    int ty = j + dy[k];
                    if (tx < 0 || ty < 0 || tx >= row || ty >= col || grid[tx][ty] == 0) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }
}
