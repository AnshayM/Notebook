package stackandqueen;

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
    public String decodeString(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack();
        int num = 0;//数字
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int index = 0;

            if (ch >= 48 && ch <= 57) {
                num = ch - '0' + num * 10;
            } else if ('[' == ch) {

                stack.add(i);
            } else if (']' == ch) {
                index = stack.pop();
                //这里开始算数
                //...
                sb.append("");//加进来
                num = 0;
            }

        }
        return null;
    }
}
