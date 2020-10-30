package anshay.notebook.leetcode;

/**
 * 回文数
 *
 * @author: Anshay
 * @date: 2019/4/17
 */
public class Solution9 {
    public static void main(String[] args) {
        boolean flag = isPalindrome(1234321);
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String str = Integer.toString(x);
        int i = 0;
        int j = str.length() - 1;
        for (i = 0; i < j; i++) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            j--;
        }
        return true;
    }

}
