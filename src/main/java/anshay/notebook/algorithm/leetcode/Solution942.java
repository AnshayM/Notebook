package anshay.notebook.algorithm.leetcode;

import anshay.notebook.common.util.MyUtil;

/**
 * 增减字符串匹配
 * <p>
 * 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。
 * <p>
 * 返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
 * <p>
 * 如果 S[i] == "I"，那么 A[i] < A[i+1]
 * 如果 S[i] == "D"，那么 A[i] > A[i+1]
 *
 * @author: Anshay
 * @date: 2019/4/22
 */
public class Solution942 {
    public static void main(String[] args) {
        String S = "IDID";
        MyUtil.print(diStringMatch(S));
    }

    public static int[] diStringMatch(String S) {
        int i = 0;
        int j = S.length();
        int[] A = new int[j + 1];
        for (int index = 0; index < S.length(); index++) {
            if (S.charAt(index) == 'I') {
                A[index] = i++;
            } else {
                A[index] = j--;

            }
        }
        A[S.length()] = i;
        return A;
    }
}
