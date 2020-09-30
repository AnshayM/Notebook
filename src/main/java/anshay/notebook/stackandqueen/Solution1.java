package anshay.notebook.stackandqueen;

/**
 * 小岛个数
 *
 * @author: Anshay
 * @date: 2019/3/28
 */
public class Solution1 {
    public static void main(String[] args) {
        char[][] a = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        char[][] b = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'}, {'0', '0', '0', '0', '1'}};
        System.out.println(numIslands(a));
        System.out.println(numIslands(b));
    }

    public static int numIslands(char[][] grid) {
        int count = 0;
        if (grid == null || grid.length <= 0 || grid[0].length <= 0) {
            return 0;
        }
        int rows = grid.length;
        int clos = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < clos; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    doSearch(grid, i, j, rows, clos);
                }
            }
        }
        return count;
    }

    public static void doSearch(char[][] grid, int i, int j, int rows, int clos) {
        if (i < 0 || i >= rows || j < 0 || j >= clos) {
            return;
        }
        if (grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        doSearch(grid, i - 1, j, rows, clos);
        doSearch(grid, i + 1, j, rows, clos);
        doSearch(grid, i, j - 1, rows, clos);
        doSearch(grid, i, j + 1, rows, clos);
    }
}
