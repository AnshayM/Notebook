package pers.anshay.notebook.interview.double_pointer;

/**
 * 88:合并两个有序数组
 * 给定两个有序整数数组  nums1 和 nums2，将 nums2 合并到  nums1  中，使得  num1 成为一个有序数组。
 * 说明:
 * 初始化  nums1 和 nums2 的元素数量分别为  m 和 n。
 * 你可以假设  nums1  有足够的空间（空间大小大于或等于  m + n）来保存 nums2 中的元素。
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出:  [1,2,2,3,5,6]
 * <p>
 * <p>
 * 题目描述：把归并结果存到第一个数组上。
 * <p>
 * 需要从尾开始遍历，否则在 nums1 上归并得到的值会覆盖还未进行归并比较的值。
 *
 * @author: Anshay
 * @date: 2019/10/12
 */
public class Solution88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m - 1, j = n - 1, k = m + n - 1; k >= 0 && j >= 0; k--) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i--];
            } else {
                nums1[k] = nums2[j--];
            }
        }
    }
}
