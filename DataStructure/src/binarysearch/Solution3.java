package binarysearch;

/**
 * 猜数字大小
 * <p>
 * 我们正在玩一个猜数字游戏。 游戏规则如下：
 * 我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
 * 每次你猜错了，我会告诉你这个我的数字是大了还是小了。
 * 你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：
 * <p>
 * 注意：start+end可能会溢出；返回值最好返回left，因为mid还是上一步的值。
 *
 * @author: Anshay
 * @date: 2019/5/29
 */
public class Solution3 {
    /**
     * @param n, your guess
     * @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
     */
    public int guessNumber(int n) {
        int start = 0;
        int end = n;
        int mid = 0;
        if (guess(n) == 0) {
            return n;
        }
        while (start < end) {
            mid = start + (end - start) / 2;
            int guessRes = guess(mid);
            if (guessRes < 0) {
                end = mid - 1;
            } else if (guessRes > 0) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return start;
    }

    public int guess(int n) {
        return 0;
    }
}
