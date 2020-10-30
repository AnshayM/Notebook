package anshay.notebook.leetcode.unsolved;

/**
 * 删除排序数组中的重复项 II
 *
 * @author machao
 * @date 2020/10/19
 */
public class Solution80 {
    public int removeDuplicates(int[] nums) {
        int course = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                count++;
            } else {
                count = 1;
            }
            //把需要的加进来
            if (count <= 2) {
                nums[course++] = nums[i];
            }

        }
        return course;
    }
}
