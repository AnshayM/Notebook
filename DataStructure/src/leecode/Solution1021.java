package leecode;

/**
 * 删除最外层的括号
 *
 * @author: Anshay
 * @date: 2019/4/19
 */
public class Solution1021 {
    public static void main(String[] args) {
        String s = "((()())(()))";
        String ss = removeOuterParentheses(s);
        System.out.println(ss);
    }

    public static String removeOuterParentheses(String S) {
        char[] ca = S.toCharArray();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < ca.length; ++i) {
            if (ca[i] == '(') {
                if (count > 0) {
                    sb.append("(");
                }
                count++;
            } else {
                count--;
                if (count != 0) {
                    sb.append(")");
                }
            }
        }
        return sb.toString();
    }
}
