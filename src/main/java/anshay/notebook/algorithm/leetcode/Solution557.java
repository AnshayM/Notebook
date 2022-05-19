package anshay.notebook.algorithm.leetcode;

/**
 * 反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * <p>
 * 常用方法
 * split,reverse,deleteCharAt
 *
 * @author: Anshay
 * @date: 2019/4/29
 */
public class Solution557 {
    public String reverseWords(String s) {
        String[] ss = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (int index = 0; index < ss.length; index++) {
            res.append(new StringBuilder(ss[index]).reverse() + " ");
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
}
