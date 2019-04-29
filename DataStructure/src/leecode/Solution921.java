package leecode;

import java.util.Stack;

/**
 * 使括号有效的最少添加
 *
 * @author: Anshay
 * @date: 2019/4/29
 */
public class Solution921 {

    // 因为逻辑确定，用这个可以少一些开销
    public int minAddToMakeValid(String S) {
        int count = 0;
        int val = 0;
        for (char ch : S.toCharArray()) {
            if (ch == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                val++;
                count = 0;
            }
        }
        return count + val;
    }

    public int minAddToMakeValid1(String S) {
        Stack<Character> stack = new Stack<>();
        for (char ch : S.toCharArray()) {
            if (ch == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                    continue;
                }
            }
            stack.add(ch);
        }
        return stack.size();
    }
}
