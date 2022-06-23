package pers.anshay.notebook.algorithm.leetcode.core;

/**
 * 63. 不同路径 II
 * 相对于62，在表格中会有一个障碍物，用1表示
 *
 * @author machao
 * @date 2022/5/17
 */
public class Solution63 {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		// 列项目
		int[] column = new int[n];
		column[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
		//从列开始遍历
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (obstacleGrid[i][j] == 1) {
					column[j] = 0;
					//遇到障碍，该行结束
					continue;
				}
				if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
					column[j] += column[j - 1];
				}
			}
		}
		return column[n - 1];
	}


	/**
	 * 有缺陷
	 * 这个方法不行，如果出现一纬的，就会有问题
	 *
	 * @param obstacleGrid
	 * @return
	 */
	public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
		if (obstacleGrid == null || obstacleGrid.length == 0) {
			return 0;
		}
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		// 用一个一维数组来表示每一行的结果
		int[][] f = new int[m][n];
		// 先确定边界的值
		for (int i = 0; i < m; i++) {
			if (obstacleGrid[i][0] == 1) {
				break;
			}
			f[i][0] = 1;
		}
		for (int j = 0; j < n; j++) {
			if (obstacleGrid[j][0] == 1) {
				break;
			}
			f[0][j] = 1;
		}
		//根据动态规划的公式累加
		for (int i = 0; i < m; i++) {
			if (obstacleGrid[i][0] == 1) {
				continue;
			}
			for (int j = 0; j < n; j++) {
				if (obstacleGrid[i][j] == 1) {
					continue;
				}
				if (obstacleGrid[i][j] == 0) {
					f[i][j] = f[i - 1][j] + f[i][j - 1];
				}
			}
		}
		return f[m - 1][n - 1];
	}
}
