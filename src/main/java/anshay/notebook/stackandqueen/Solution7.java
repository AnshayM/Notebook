package anshay.notebook.stackandqueen;

import java.util.Stack;

/**
 * 字符串解码
 * <p>
 * 思路：遍历每一个字符，用栈记录 "[",的下标，遇到"]"时比较栈顶，如果时“[”就pop，然后计算这一段的字符。，并记住[的位置用以排序重组字符串
 *
 * @author: Anshay
 * @date: 2019/4/29
 */
public class Solution7 {
    public static void main(String[] args) {
        String res = decodeString("3[a]2[bc]");
    }

    public static String decodeString(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (']' == s.charAt(i)) {
                // 对应字符串
                String str = "";
                while (!"[".equals(stack.peek())) {
                    str = stack.pop() + str;
                }
                stack.pop();

                // 数字
                String countStr = "";
                while (!stack.isEmpty() && (stack.peek().charAt(0) >= '0' && stack.peek().charAt(0) <= '9')) {
                    countStr = stack.pop() + countStr;
                }
                int count = Integer.parseInt(countStr);
                String resStr = "";
                for (int j = 0; j < count; j++) {
                    resStr += str;
                }
                stack.push(resStr);
            } else {
                String str = "" + s.charAt(i);
                stack.push(str);
            }
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        return res;
    }
}
