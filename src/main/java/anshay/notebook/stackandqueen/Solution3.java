package anshay.notebook.stackandqueen;

/**
 * 完全平方数
 *
 * @author: Anshay
 * @date: 2019/4/1
 */
public class Solution3 {
    public int numSquares(int n) {
        while (n % 4 == 0) {
            n /= 4;
        }
        if (n % 8 == 7) {
            return 4;
        }
        for (int a = 0; a * a <= n; a++) {
            int b = (int) Math.sqrt(n - a * a);
            if (a * a + b * b == n) {
                if (a == 0) {
                    return 1;
                }
                return 2;
            }
        }
        return 3;
    }

}
