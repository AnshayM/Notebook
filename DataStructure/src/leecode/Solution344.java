package leecode;

import java.util.Stack;

/**
 * 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * @author: Anshay
 * @date: 2019/4/29
 */
public class Solution344 {
    public void reverseString(char[] s) {
        int m = s.length;
        char temp;
        for (int i = 0; i < m / 2; i++) {
            temp = s[i];
            s[i] = s[m - 1 - i];
            s[m - 1 - i] = temp;
        }
    }

    /*工具类是有代价的*/
    public void reverseString1(char[] s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s) {
            stack.add(ch);
        }
        for (int i = 0; i < s.length; i++) {
            s[i] = stack.pop();
        }
    }
}
