package pers.anshay.notebook.algorithm.leetcode.old;

/**
 * 数字的补数
 * 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
 * <p>
 * 注意:
 * <p>
 * 给定的整数保证在32位带符号整数的范围内。
 * 你可以假定二进制数不包含前导零位。
 *
 * @author: Anshay
 * @date: 2019/4/23
 */
public class Solution476 {
    public static void main(String[] args) {
        int a = findComplement(8);
    }

    public static int findComplement(int num) {
        int result = 0;
        for (int i = 0; num > 0; i++) {
            result += (1 - num % 2) << i;
            num /= 2;
        }
        return result;
    }

    /*基本上不会用的的api方法*/
    public static int findComplement1(int num) {
        int mask = (Integer.highestOneBit(num) << 1) - 1;
        return num ^ mask;
    }
}
