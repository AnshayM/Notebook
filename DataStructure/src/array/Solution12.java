package array;

/**
 * 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 说明:
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的原地算法。
 *
 * @author: Anshay
 * @date: 2019/5/15
 */
public class Solution12 {
    /*循环k次，每次往后挪一位*/
    public void rotate1(int[] nums, int k) {
        int length = nums.length;
        int temp = nums[length - 1];
        for (int i = 0; i < k; i++) {
            for (int j = length - 1; j >= 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = temp;
        }
    }

    /*数组拼接,注意越界*/
    public void rotate2(int[] nums, int k) {
//        k %= nums.length;
//        int[] res = new int[k];
//        System.arraycopy(nums, nums.length - k, res, 0, k);
//        System.arraycopy(nums, 0, nums, k , nums.length - k);
//        System.arraycopy(res, 0, nums, 0, k);
    }
}
