package anshay.notebook.leetcode;

/**
 * 数组拆分 I
 * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。
 * 提示：n 是正整数,范围在 [1, 10000].
 * 数组中的元素范围在 [-10000, 10000].
 * <p>
 * 考察排序。
 * <p>
 * 解法2（更快）：
 * 用另一个数组，把值变成下标，对应值置1.这样新数组里值为1的元素下标就是原数组所有元素。
 * 然后判断新数组的值是否大于1，然后隔一个加一个就可以了。
 * 注意：注意数组的下标范围，大于0小于length
 *
 * @author: Anshay
 * @date: 2019/4/26
 */
public class Solution561 {

    public static void main(String[] args) {
        int[] nums = {1, 4, 3, 2};
        int sum = arrayPairSum(nums);

    }

    public static int arrayPairSum(int[] nums) {
        int sum = 0;
        int offset = 10000;
        int[] exist = new int[20001];
        for (int i = 0; i < nums.length; i++) {
            exist[nums[i] + offset]++;
        }
        // 隔一个加一个
        boolean add = true;
        for (int i = 0; i < 20000; i++) {
            if (exist[i] == 0) {
                continue;
            }
            sum += exist[i] / 2 * (i - offset);
            if (exist[i] % 2 == 1) {
                sum = add ? sum + i - offset : sum;
                add = !add;
            }
        }
        return sum;
    }

}
