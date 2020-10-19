package pers.anshay.notebook;

/**
 * @author machao
 * @date 2020/10/19
 */
public class Test {
    public int removeElement(int[] nums, int val) {
        int i =0;
        for (int i1 = 0; i1 < nums.length; i1++) {
            if (nums[i1] != val) {
                nums[i++] = nums[i1];
            }
        }
        return i;
    }
}
