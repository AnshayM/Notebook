package array;
/**
 * 至少是其他数字两倍的最大数,即找最大的两个数，然后max1>=max2*2
 *
 * @author: Anshay
 * @date: 2019/4/11
 */
public class Solution2 {
    public int dominantIndex(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int max1 = 0;
        int max2 = 0;
        if (nums[1] < nums[0]) {
            max2 = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[max1]) {
                max2 = max1;
                max1 = i;
            } else if (nums[i] > nums[max2]) {
                max2 = i;
            }
        }
        if (nums[max1] >= nums[max2] * 2) {
            return max1;
        }
        return -1;
    }

}
