package anshay.notebook.leecode.unsolved;

/**
 * 26. 删除排序数组中的重复项
 *
 * @author machao
 * @date 2020/10/19
 */
public class Solution26 {
    public int removeDuplicates(int[] nums) {
        int course = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[course] != nums[i]) {
                //这里不一样，不相等时将下一位的值设为指定值
                nums[++course] = nums[i];
            }
        }
        //这里是下角标
        return course + 1;
    }
}
