package pers.anshay.notebook.interview.double_pointer;

/**
 * 345. 反转字符串中的元音字母
 *
 * 思路：带条件的双指针
 *
 * @author machao
 * @date 2021/2/25
 */
public class Solution345 {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String vowels = "aeiouAEIOU";

        int left = 0, right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left < right) {
            char lChar = s.charAt(left);
            char rChar = s.charAt(right);
            if (vowels.indexOf(lChar) > -1) {
                left++;
            } else if (vowels.indexOf(rChar) > -1) {
                right--;
            } else {
                chars[left++] = rChar;
                chars[right--] = lChar;
            }
        }
        return new String(chars);
    }
}
