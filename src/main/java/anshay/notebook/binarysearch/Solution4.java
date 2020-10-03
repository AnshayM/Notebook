package anshay.notebook.binarysearch;

/**
 * 搜索旋转排序数组
 * <p>
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * <p>
 * 思路：将数组一分为二，一定有一半有序有一半无序。判断有序的有效性，循环/递归查找无序部分
 * 先判断是哪一部分有序，再判断在这部分中，选中哪一半去搜索
 * 每次调用，范围缩小为原来的1/4
 *
 * @author: Anshay
 * @date: 2019/5/29
 */
public class Solution4 {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 1, 2, 3, 4};
        int a = search(nums, 1);
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return search(nums, 0, nums.length - 1, target);
    }

    /*递归*/
    public static int search(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        /*通过nums[mid] 比较 start判断前后哪个部分有序*/
        if (nums[mid] < nums[end]) {
            /*后半部分有序，且在有序区间内*/
            if (nums[mid] < target && target <= nums[end]) {
                return search(nums, mid + 1, end, target);
            } else {
                return search(nums, start, mid - 1, target);
            }
        } else {
            /*前半部分有序，且在有序区间内*/
            if (nums[start] <= target && target < nums[mid]) {
                return search(nums, start, mid - 1, target);
            } else {
                return search(nums, mid + 1, end, target);
            }
        }
    }

    /*循环*/
    public static int search1(int[] nums, int start, int end, int target) {
        int mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            /*通过nums[mid] 比较 start判断前后哪个部分有序*/
            if (nums[mid] < nums[end]) {
                /*后半部分有序，且在有序区间内*/
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                /*前半部分有序，且在有序区间内*/
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

        }
        return -1;

    }
}
