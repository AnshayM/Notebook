package anshay.notebook.learn.array;

/**
 * 寻找数组的中心索引
 * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * <p>
 * 解法：算总和sum后，从左侧依次元素值加到sumLeft,比较sumleft*2 和sum-nums[i]是否相等
 * 如果数量较多，可以从sumleft和sumRight两头作和比较。这样时间维度消耗低。
 *
 * @author: Anshay
 * @date: 2019/4/11
 */
public class Solution3_1 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 7, 3, 6, 5, 6};
        int a = (int) pivotIndex(nums);
    }

    public static int pivotIndex(int[] nums) {
        int sum = 0;
        int sumLeft = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            if (sumLeft * 2 == sum - nums[i]) {
                return i;
            }
            sumLeft += nums[i];
        }
        return -1;
    }
}
