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
    /**
     * 双重循环
     * 时间复杂度：O(kn)
     * 空间复杂度：O(1)
     */
    public void rotate1(int[] nums, int k) {
        int len = nums.length;
        k %= nums.length;
        for (int i = 0; i < k; i++) {
            int temp = nums[len - 1];
            for (int j = len - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = temp;
        }
    }

    /**
     * 翻转
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void rotate2(int[] nums, int k) {
        int len = nums.length;
        k %= nums.length;
        /*先整体翻转，再翻转前K个，再翻转后面的*/
        reverser(nums, 0, len - 1);
        reverser(nums, 0, k - 1);
        reverser(nums, k, len - 1);

    }

    public void reverser(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

}
