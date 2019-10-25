package leecode.easy;

/**
 * 最大子序和
 * <p>
 * 简单思路：遍历数组，然后计算每一步的sum，每一步取较大者更新sum，。
 * 为负则放弃之前的数
 *
 * @author: Anshay
 * @date: 2019/10/24
 */
public class S53MaximumSubarray {
    public int maxSubArray(int[] nums) {
        //用于存储最大的结果
        int res = nums[0];
        //用于存储当前最大的子序和
        int sum = 0;
        for (int num : nums) {
            // 如果sum是非负数，就累积。如果是负数就放弃之前的结果从重新累积
            if (sum >= 0) {
                sum += num;
            } else {
                sum = num;
            }
            //更新最大值
            res = Math.max(res, sum);
        }
        return res;
    }
}
