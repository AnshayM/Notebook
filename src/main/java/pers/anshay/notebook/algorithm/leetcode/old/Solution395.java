package pers.anshay.notebook.algorithm.leetcode.old;

/**
 * 395. 至少有K个重复字符的最长子串
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 *
 * @author machao
 * @date 2021/2/27
 */
public class Solution395 {
    public int longestSubstring(String s, int k) {
        int[] times = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            times[index] += 1;
        }
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (times[index] < k) {
                for (String subStr : s.split(String.valueOf(s.charAt(i)))) {
                    maxLength = Math.max(maxLength, longestSubstring(subStr, k));
                }
                return maxLength;
            }
        }
        return s.length();
    }
}
