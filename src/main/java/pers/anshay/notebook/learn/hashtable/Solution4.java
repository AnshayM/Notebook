package pers.anshay.notebook.learn.hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * 快乐数
 * 编写一个算法来判断一个数是不是“快乐数”。
 * <p>
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，
 * 也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 * <p>
 * 思路：
 * 1：用set存每一次的结果，找到重复时判断当前数字是不是1
 * 2：可以看作一个循环链表，使用slow和fast两个游标去遍历
 *
 * @author: Anshay
 * @date: 2019/5/23
 */
public class Solution4 {
    public static void main(String[] args) {
        boolean res = isHappy(19);
    }

    /*可以看作循环，使用slow、fast两个游标，这个速度快一些，并且未使用工具类*/
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = doHappy(n);
        while (slow != fast) {
            slow = doHappy(slow);
            fast = doHappy(doHappy(fast));
        }
        return slow == 1;
    }

    /*使用Set*/
    public static boolean isHappy1(int n) {
        Set<Integer> set = new HashSet<>();
        int cur = n;
        while (set.add(cur)) {
            cur = doHappy(cur);
        }
        return cur == 1;
    }

    public static int doHappy(int n) {
        int res = 0;
        int temp = 0;
        while (n > 0) {
            temp = n % 10;
            res += temp * temp;
            n /= 10;
        }
        return res;
    }
}
