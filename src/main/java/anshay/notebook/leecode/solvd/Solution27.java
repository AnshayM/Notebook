package anshay.notebook.leecode.solvd;

/**
 * 类似移除0-可跳过
 *
 * @author machao
 * @date 2020/10/19
 */
public class Solution27 {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int i1 = 0; i1 < nums.length; i1++) {
            if (nums[i1] != val) {
                nums[i++] = nums[i1];
            }
        }
        return i;
    }
}
