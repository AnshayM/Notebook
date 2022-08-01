package pers.anshay.notebook.algorithm.leetcode.core;

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
        // 小于0或者0结尾，都不是回文数
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }
        String str = Integer.toString(x);
        int i = 0;
        int j = str.length() - 1;
        for (i = 0; i < j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 不转化为字符串解决
     * 奇数位时，将中位数和后续数反转后得到revertedNumber，revertedNumber/10必然和中位数以前的数字相等
     * 偶数位时，两边都相等
     * 不论奇数/偶数，维持遍历的限制边界条件都是x>revertedNumber
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome2(int x) {
        // 小于0或者0结尾，都不是回文数
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }

}
