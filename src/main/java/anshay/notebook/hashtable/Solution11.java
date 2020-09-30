package anshay.notebook.hashtable;

/**
 * 有效的数独
 * <p>
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * <p>
 * 分析：只需要判断现有的数字是否合理。即分别在行、列、子九格这三个维度里判断是否有重复数字。
 * i/3 *3 +j,可以将九个子九格分开，用boolean的数组判断9个数字是否有重复（已出现的为true）
 *
 * @author: Anshay
 * @date: 2019/5/27
 */
public class Solution11 {
    public boolean isValidSudoku(char[][] board) {
        /*记录某行，某位数字是否已经被摆放*/
        boolean[][] row = new boolean[9][9];
        /*记录某列，某位数字是否已经被摆放*/
        boolean[][] col = new boolean[9][9];
        /*记录某3x3宫格内，某位数字是否已经被摆放*/
        boolean[][] block = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' == board[i][j]) {
                    continue;
                }
                // 对应的数字1-9，但是下标从0开始
                int num = board[i][j] - '1';
                int blockIndex = i / 3 * 3 + j / 3;
                if (row[i][num] || col[j][num] || block[blockIndex][num]) {
                    return false;
                } else {
                    row[i][num] = true;
                    col[j][num] = true;
                    block[blockIndex][num] = true;
                }
            }
        }
        return true;
    }
}
