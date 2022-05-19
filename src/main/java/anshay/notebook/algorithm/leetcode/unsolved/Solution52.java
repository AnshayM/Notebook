package anshay.notebook.algorithm.leetcode.unsolved;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * 52. N皇后 II
 * 题解：落下每个子时，保证所有横/列（Integer值即表示第几行/列）、左斜（x+y即表示一条斜线）集合和右斜（x-y即表示一条斜线）集合里都没有子
 * <p>
 * 由于每个皇后必须位于不同列，因此已经放置的皇后所在的列不能放置别的皇后。第一个皇后有 N 列可以选择，第二个皇后最多有 N-1 列可以选择
 * ，第三个皇后最多有 N-2 列可以选择（如果考虑到不能在同一条斜线上，可能的选择数量更少），因此所有可能的情况不会超过 N!种，
 * 遍历这些情况的时间复杂度是 O(N!)O(N!)。
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/n-queens-ii/solution/nhuang-hou-ii-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author machao
 * @date 2020/10/17
 */
@Slf4j
public class Solution52 {
    public static void main(String[] args) {
        int solution = solution(2);
        log.info("值:{}", solution);
    }

    static int solution(int n) {
        if (n <= 0) {
            return 0;
        }
        //横
        Set<Integer> set1 = new HashSet<>();
        //左斜
        Set<Integer> set2 = new HashSet<>();
        //右斜
        Set<Integer> set3 = new HashSet<>();
        return backtrack(n, 0, set1, set2, set3);
        // return backtrack2(n, 0, set1, set2, set3);
    }

    /**
     * 对每一行按列遍历
     */
    static int backtrack(int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!columns.add(i)) {
                continue;
            }
            if (!diagonals1.add(row + i)) {
                continue;
            }
            if (!diagonals2.add(row - i)) {
                continue;
            }
            count += backtrack(n, row + 1, columns, diagonals1, diagonals2);
            //这里只是计算有多少种解法，所以放置后将位置移除来
            columns.remove(i);
            diagonals1.remove(row + i);
            diagonals2.remove(row - i);
        }
        return count;
    }

    static int backtrack2(int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (columns.contains(i)) {
                continue;
            }
            if (diagonals1.contains(row + i)) {
                continue;
            }
            if (diagonals2.contains(row - i)) {
                continue;
            }
            columns.add(i);
            diagonals1.add(row + i);
            diagonals2.add(row - i);
            count += backtrack2(n, row + 1, columns, diagonals1, diagonals2);
            //这里只是计算有多少种解法，所以放置后将位置移除来
            columns.remove(i);
            diagonals1.remove(row + i);
            diagonals2.remove(row - i);
        }
        return count;
    }
}
