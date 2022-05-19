package anshay.notebook.algorithm.leetcode;

/**
 * 机器人能否返回原点
 * 使用ascii码数组要更快一些，也省内存，代码也整洁。ascii一共有128位。a-65,A-97
 *
 * @author: Anshay
 * @date: 2019/4/22
 */
public class Solution657 {
    public boolean judgeCircle(String moves) {
        int row = 0;
        int clo = 0;
        for (char ch : moves.toCharArray()) {
            switch (ch) {
                case 'R':
                    row += 1;
                    break;
                case 'L':
                    row -= 1;
                    break;
                case 'U':
                    clo += 1;
                    break;
                case 'D':
                    clo -= 1;
                    break;
                default:
                    break;
            }
        }
        if (row == 0 && clo == 0) {
            return true;
        }
        return false;
    }

    public boolean judgeCircle1(String moves) {
        int[] cnt = new int[128];
        for (char ch : moves.toCharArray()) {
            cnt[ch]++;
        }
        return cnt['L'] == cnt['R'] && cnt['U'] == cnt['D'];
    }
}
