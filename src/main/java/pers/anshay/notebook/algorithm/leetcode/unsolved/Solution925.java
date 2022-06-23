package pers.anshay.notebook.algorithm.leetcode.unsolved;

/**
 * 比较typed和标准比较不等时，比较type[n]和type[n-1]
 * 注意数组越界
 *
 * @author machao
 * @date 2020/10/21
 */
public class Solution925 {
    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        //执行完后i是加了1的
        return i == name.length();
    }
}
