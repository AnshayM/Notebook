package anshay.notebook.learn.hashtable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 两个数组的交集
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 * @author: Anshay
 * @date: 2019/5/23
 */
public class Solution3 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 4, 5, 65};
        int[] nums2 = new int[]{1, 2, 3, 4, 5, 65};
    }

    /**/
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int item : nums1) {
            set.add(item);
        }
        for (int item : nums2) {
            if (set.contains(item)) {
                list.add(item);
                set.remove(item);
            }
        }
        int[] res = new int[list.size()];
        int index = 0;
        for (int item : list) {
            res[index++] = item;
        }
        return res;
    }

    /*2个set来做*/
    public static int[] intersection1(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int item : nums1) {
            set1.add(item);
        }
        for (int item : nums2) {
            if (set1.contains(item)) {
                set2.add(item);
            }
        }
        int[] res = new int[set2.size()];
        int index = 0;
        for (int item : set2) {
            res[index++] = item;
        }
        return res;
    }
}
