package pers.anshay.notebook.algorithm.leetcode.old;

import java.util.ArrayList;
import java.util.List;

/**
 * 自除数
 * 自除数 是指可以被它包含的每一位数除尽的数。
 * 例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 还有，自除数不允许包含 0 。
 *
 * @author: Anshay
 * @date: 2019/4/30
 */
public class Solution728 {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (selfDivide(i)) {
                list.add(i);
            }
        }
        return list;
    }

    private boolean selfDivide(int num) {
        int data = num;
        while (num != 0) {
            int p = num % 10;
            if (p == 0) {
                return false;
            }
            if (data % p != 0) {
                return false;
            }
            num = num / 10;
        }
        return true;
    }

    public List<Integer> selfDividingNumbers1(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (; left <= right; left++) {
            // 这一步比较耗时
            String numStr = String.valueOf(left);
            if (numStr.indexOf("0") != -1) {
                continue;
            }
            boolean isSelfDividingNumbers = true;
            for (int i = 0; i < numStr.length(); i++) {
                int divisor = numStr.charAt(i) - '0';
                if (left % divisor != 0) {
                    isSelfDividingNumbers = false;
                    break;
                }
            }
            if (isSelfDividingNumbers) {
                list.add(left);
            }
        }
        return list;
    }
}
