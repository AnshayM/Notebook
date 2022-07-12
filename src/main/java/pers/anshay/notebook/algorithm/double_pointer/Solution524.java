package pers.anshay.notebook.algorithm.double_pointer;

import java.util.List;

/**
 * 524. 通过删除字母匹配到字典里最长单词
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * <p>
 * 输出:
 * "apple"
 * 示例   2:
 * <p>
 * 输入:
 * s = "abpcplea", d = ["a","b","c"]
 * <p>
 * 输出:
 * "a"
 * 说明:
 * <p>
 * 所有输入的字符串只包含小写字母。
 * 字典的大小不会超过 1000。
 * 所有输入的字符串长度不会超过 1000。
 *
 * @author machao
 * @date 2021/2/25
 */
public class Solution524 {
    public String findLongestWord(String s, List<String> dictionary) {
        String longestStr = "";
        for (String item : dictionary) {
            if (longestStr.length() > item.length()
                    || (longestStr.length() == item.length() && longestStr.compareTo(item) < 0)) {
                continue;
            }
            int i = 0, j = 0;
            //两个字符较长时，要比较偏移量和两字符长度差
            while (i < s.length() && j < item.length()) {
                if (s.charAt(i) == item.charAt(j)) {
                    j++;
                }
                i++;
            }
            if (j == item.length()) {
                longestStr = item;
            }
        }
        return longestStr;
    }
}
