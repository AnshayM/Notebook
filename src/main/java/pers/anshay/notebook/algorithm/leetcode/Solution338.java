package pers.anshay.notebook.algorithm.leetcode;

/**
 * 338. 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * 思路，可以复用前面的值
 * x>>2(x/2)的值已经有了，再比较个位数x&i
 *
 * @author machao
 * @date 2021/3/3
 */
public class Solution338 {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }
}
