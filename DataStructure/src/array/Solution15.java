package array;

/**
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * @author: Anshay
 * @date: 2019/5/15
 */
public class Solution15 {
    /*直接从头部覆盖*/
    public void moveZeroes(int[] nums) {
        int cursor = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[cursor++] = nums[i];
            }
        }
        for (int i = cursor; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public void moveZeroes1(int[] nums) {
        int end = nums.length - 1;
        int temp;
        int i = 0;
        while (i < end) {
            while (nums[i] != 0 && i < end) {
                i++;
            }
            temp = nums[i];
            for (int j = i; j < end; j++) {
                nums[j] = nums[j + 1];
            }
            nums[end--] = temp;
            i += nums[i] == 0 ? 0 : 1;
        }
    }
}
