package pers.anshay.notebook.algorithm.leetcode;

/**
 * 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * @author: Anshay
 * @date: 2019/4/22
 */
public class Solution461 {
    public int hammingDistance(int x, int y) {
        int count = 0;
        while (x > 0 || y > 0) {
            count += x % 2 == y % 2 ? 0 : 1;
            x /= 2;
            y /= 2;
        }
        return count;
    }

    /* >>标识二进制右移动多少位，& 标识按位与 ，^ 标识异或*/
    public int hammingDistance1(int x, int y) {
        int tmp = x ^ y;
        int result = 0;

        for (int i = 0; i < 32; i++) {
            result += (tmp >> i) & 1;
        }
        return result;
    }
}
