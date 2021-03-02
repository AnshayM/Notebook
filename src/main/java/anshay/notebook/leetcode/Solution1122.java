package anshay.notebook.leetcode;

/**
 * 1122. 数组的相对排序
 * 给你两个数组，arr1 和arr2，
 * <p>
 * arr2中的元素各不相同
 * arr2 中的每个元素都出现在arr1中
 * 对 arr1中的元素进行排序，使 arr1 中项的相对顺序和arr2中的相对顺序相同。未在arr2中出现过的元素需要按照升序放在arr1的末尾。
 * <p>
 *
 * <p>
 * 示例：
 * <p>
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *
 * <p>
 * 提示：
 * <p>
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2中的元素arr2[i]各不相同
 * arr2 中的每个元素arr2[i]都出现在arr1中
 *
 * @author machao
 * @date 2020/11/14
 */
public class Solution1122 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        //将arr1装入桶中,下标是数值，value为出现次数
        int[] bucket = new int[1001];
        int[] res = new int[arr1.length];
        for (int i : arr1) {
            bucket[i]++;
        }
        //将桶中对应arr2的数据放到res前半部中
        int j = 0;
        for (int i : arr2) {
            while (bucket[i]-- > 0) {
                res[j++] = i;
            }
        }
        //按照升序，将桶中剩余的加到res中
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                res[j++] = i;
            }
        }
        return res;
    }
}
