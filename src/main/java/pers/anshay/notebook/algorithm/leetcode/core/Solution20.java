package pers.anshay.notebook.algorithm.leetcode.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效的括号
 *
 * @author: Anshay
 * @date: 2019/4/3
 */
public class Solution20 {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (int i = 0; i < s.length(); i++) {
            char item = s.charAt(i);
            if (stack.isEmpty()) {
                // 如果是反括号
                if (map.containsKey(item)) {
                    return false;
                }
                stack.push(item);
            } else if (stack.peek() == map.get(item)) {
                stack.pop();
            } else {
                // 如果是反括号
                if (map.containsKey(item)) {
                    return false;
                }
                stack.push(item);
            }
        }
        return stack.isEmpty();
    }
}
