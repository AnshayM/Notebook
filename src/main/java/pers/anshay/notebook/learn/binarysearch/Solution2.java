package pers.anshay.notebook.learn.binarysearch;

/**
 * x 的平方根
 * <p>
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 注意：int数据大小范围
 *
 * @author: Anshay
 * @date: 2019/5/28
 */
public class Solution2 {
    public static void main(String[] args) {
        int a = mySqrt(2147395599);
    }

    public static int mySqrt(int x) {
        long left = 0;
        long right = x;
        while (left < right && left * left < x) {
            long mid = (left + right) / 2;
            if (mid * mid < x) {
                left = mid + 1;
            } else if (mid * mid > x) {
                right = mid - 1;
            } else {
                return (int) mid;
            }
        }
        return (int) (left * left <= x ? left : left - 1);
    }

    /* 神秘数字0x5f3759df */
    public int mySqrt1(int x) {
        long t = x;
        t = 0x5f3759df - (t >> 1);
        while (!(t * t <= x && (t + 1) * (t + 1) > x))
            t = (x / t + t) / 2;
        return (int) t;
    }
}
