package pers.anshay.notebook.algorithm.leetcode.core;

/**
 * 最长回文子串
 *
 * @author machao
 * @date 2022/5/12
 */
public class TSolution5 {

	/**
	 * 动态规划
	 * 1。用一个二维数组boolean[m][n]表示从i到j是否为回文
	 * 2。P(i,j)=P(i+1,j−1)∧(Si ===Sj)
	 *
	 * @param
	 * @return
	 */
	public String longestPalindrome(String s) {
		if (s == null || s.length() < 2) {
			return s;
		}
		int len = s.length();

		//二维数组表示i到j是否为回文
		boolean[][] dp = new boolean[len][len];
		for (int i = 0; i < len; i++) {
			dp[i][i] = true;
		}

		char[] chars = s.toCharArray();
		//初始值，1
		int maxLen = 1;
		int begin = 0;
		for (int L = 2; L <= len; L++) {
			for (int i = 0; i < len; i++) {
				// 右边界 j-i+1=L
				int j = L + i - 1;
				if (j >= len) {
					break;
				}
				if (chars[i] != chars[j]) {
					dp[i][j] = false;
				} else {
					// 是否已经最小，AA或者ABA类型
					if (j - i < 3) {
						dp[i][j] = true;
					} else {
						dp[i][j] = dp[i + 1][j - 1];
					}
				}
				// 更新最长值和begin位置
				if (dp[i][j] && j - i + 1 > maxLen) {
					maxLen = j - i + 1;
					begin = i;
				}
			}

		}
		return s.substring(begin, begin + maxLen);
	}

	/**
	 * 中心扩展
	 *
	 * @param s
	 * @return
	 */
	public String longestPalindrome2(String s) {
		if (s == null || s.length() < 2) {
			return s;
		}
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}


		}

		return s.substring(start, end + 1);
	}

	public int expandAroundCenter(String s, int left, int right) {
		while (left > 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			--left;
			++right;
		}
		return right - left - 1;
	}

}
