package com.datastructure.stackandqueen;

import java.util.Stack;

/**
 * 根据逆波兰表示法，求表达式的值。
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 *
 * 循环或者递归两种方法
 *
 * @author: Anshay
 * @date: 2019/4/10
 */
public class Solution6 {
    public static void main(String[] args) {
        evalRPN(new String[]{"0", "3", "/"});
    }

    // 已经说明了给定逆波兰表达式总是有效的，所以不再加验证条件
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < tokens.length; i++) {
            if ("+-*/".indexOf(tokens[i]) > -1 && stack.size() >= 2) {
                int b = stack.pop();//取出往前第1个
                int a = stack.pop();//取出往前第2个
                switch (tokens[i]) {
                    case "+":
                        stack.add(a + b);
                        break;
                    case "-":
                        stack.add(a - b);
                        break;
                    case "*":
                        stack.add(a * b);
                        break;
                    case "/":
                        stack.add(a / b);
                        break;
                }
            } else {
                stack.add(Integer.parseInt(tokens[i]));
            }
        }
        //最后应该只有一个元素，但是不做验证了
        return stack.peek();
    }

}
