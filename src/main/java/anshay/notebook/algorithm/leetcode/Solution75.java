package anshay.notebook.algorithm.leetcode;

/**
 * 75 颜色分类
 * 给定一个包含红色、白色和蓝色，一共n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author machao
 * @date 2020/10/20
 */
public class Solution75 {
    public void sortColors(int[] nums) {
        //整理好的1(可能长度为0)后的第一个角标
        int pre0 = 0;
        //整理好的2(可能长度为0)后的第一个角标
        int pre1 = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 0) {
                nums[i] = nums[pre0];
                nums[pre0] = num;
                //已经有2被排列到前面时,前面被替换到nums[i]的数字是已经排序好的2，所以需要换回来
                if (pre0 < pre1) {
                    int temp = nums[i];
                    nums[i] = nums[pre1];
                    nums[pre1] = temp;
                }
                pre0++;
                pre1++;
            } else if (num == 1) {
                nums[i] = nums[pre1];
                nums[pre1] = num;
                pre1++;
            }
        }
    }
}
