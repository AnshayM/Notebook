package pers.anshay.notebook;

/**
 * @author machao
 * @date 2020/10/19
 */
public class Test {
    public int removeDuplicates2(int[] nums) {
        int course = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[course + 1] != nums[i] && course + 1 < nums.length) {
                //这里不一样，不相等时将下一位的值设为指定值
                nums[++course] = nums[i];
            }
        }
        //这里是下角标
        return course + 1;
    }
}
