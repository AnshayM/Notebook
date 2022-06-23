package pers.anshay.notebook.algorithm.leetcode.core;

/**
 * 62 不同路径
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * 思路：动态规划
 * f(i,j)=f(i-1,j)+f(i,j-1)
 * 将f(0,j)和f(i,0)设置为边界条件，值都是1
 *
 * @author machao
 * @date 2022/5/17
 */
public class Solution62 {

	public int uniquePaths(int m, int n) {
		// 判断长度
		if (m == 0 || n == 0) {
			return 0;
		} else if (m == 1 || n == 1) {
			return 1;
		}
		//创建一个二维数组
		int[][] f = new int[m][n];
		// 先确定边界的值
		for (int i = 0; i < m; i++) {
			f[i][0] = 1;
		}
		for (int j = 0; j < n; j++) {
			f[0][j] = 1;
		}
		//根据动态规划的公式累加
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				f[i][j] = f[i - 1][j] + f[i][j - 1];
			}
		}
		return f[m - 1][n - 1];
	}
}
