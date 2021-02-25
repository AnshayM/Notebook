package anshay.notebook.interview.double_pointer;

/**
 * 680:验证回文字符串 Ⅱ
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * @author machao
 * @date 2021/2/25
 */
public class Solution680 {
    public boolean validPalindrome(String s) {
        for (int l = 0, r = s.length() - 1; l < r; l++, r--) {
            if (s.charAt(l) != s.charAt(r)) {
                return isPalindrome(s, l + 1, r) || isPalindrome(s, l, r - 1);
            }
        }
        return true;
    }

    public boolean isPalindrome(String s, int l, int r) {
        for (; l < r; l++, r--)
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
        return true;
    }
}
