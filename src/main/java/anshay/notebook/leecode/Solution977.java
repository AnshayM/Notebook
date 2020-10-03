package anshay.notebook.leecode;

/**
 * 有序数组的平方
 * <p>
 * 思路：双头并进，取平方数,取大的存进stack  进：先大后小。出，先小后大
 * 一个一个退栈到新数组，长度是一致的
 * <p>
 * 可以不用工具类，直接存到对应数组
 *
 * @author: Anshay
 * @date: 2019/4/19
 */
public class Solution977 {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, -2, -1, 0, 2, 3, 3, 4};
        int[] nus = sortedSquares(nums);
        System.out.println(nus);
    }

    public static int[] sortedSquares(int[] A) {
        int length = A.length;
        int i = 0;
        int j = length - 1;
        int[] nums = new int[length];
        while (i <= j) {
            // 直接算要省内存
//            int a = (int) Math.pow(A[i], 2);
//            int b = (int) Math.pow(A[j], 2);
            int a = A[i] * A[i];
            int b = A[j] * A[j];
            if (a >= b) {
                nums[--length] = a;
                i++;
            } else {
                nums[--length] = b;
                j--;
            }
        }

        return nums;
    }
}
