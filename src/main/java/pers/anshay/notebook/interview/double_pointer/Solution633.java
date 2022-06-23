package pers.anshay.notebook.interview.double_pointer;

/**
 * 633. 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 * 167的进阶
 *
 * @author machao
 * @date 2021/2/25
 */
public class Solution633 {
    public boolean judgeSquareSum(int c) {
        if (c < 0) {
            return false;
        }
        int i = 0;
        int j = (int) Math.sqrt(c);
        while (i <= j) {
            int pwsSum = i * i + j * j;
            if (pwsSum > c) {
                j--;
            } else if (pwsSum < c) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }
}
