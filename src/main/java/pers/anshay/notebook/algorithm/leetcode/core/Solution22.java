package pers.anshay.notebook.algorithm.leetcode.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 回溯方法求解
 *
 * @author machao
 * @date 2022/5/20
 */
public class Solution22 {
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		doGenerateParenthesis(res, new StringBuilder(), n, n);
		return res;
	}

	private void doGenerateParenthesis(List<String> res, StringBuilder sb, int left, int right) {
		if (right == 0) {
			res.add(sb.toString());
			return;
		}
		if (left > 0) {
			StringBuilder sbNew = new StringBuilder(sb.toString());
			sbNew.append('(');
			left--;
			doGenerateParenthesis(res, sbNew, left, right);
		}
		if (right > 0 && right > left) {
			StringBuilder sbNew = new StringBuilder(sb.toString());
			sbNew.append(')');
			right--;
			doGenerateParenthesis(res, sbNew, left, right);
		}
	}
}
