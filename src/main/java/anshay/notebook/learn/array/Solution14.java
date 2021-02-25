package anshay.notebook.learn.array;

/**
 * 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * @author: Anshay
 * @date: 2019/5/15
 */
public class Solution14 {
    public static void main(String[] args) {
        String res = reverseWords("the sky is blue");
    }

    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] strings = s.trim().split(" ");
        for (int i = 0; i < strings.length; i++) {
            String str = strings[strings.length - 1 - i];
            if (!"".equals(str)) {
                sb.append(str);
                if (i != strings.length - 1) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }
}
