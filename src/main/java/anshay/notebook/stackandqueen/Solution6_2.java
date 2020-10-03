package anshay.notebook.stackandqueen;

/**
 * @author: Anshay
 * @date: 2019/4/10
 */
public class Solution6_2 {
    //定义初始量
    private int n = -1;

    public int evalRPN(String[] tokens) {
        if (n == -1) {
            n = tokens.length - 1;
        }
        String s = tokens[n--];
        char c = s.charAt(0);
        if (s.length() == 1 && "+-*/".indexOf(c) != -1) {
            int a = evalRPN(tokens);
            int b = evalRPN(tokens);
            switch (c) {
                case '+':
                    return a + b;
                case '-':
                    return a - b;
                case '*':
                    return a * b;
                case '/':
                    return a / b;
            }
        }
        return Integer.parseInt(s);
    }
}
