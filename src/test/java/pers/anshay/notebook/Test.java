package pers.anshay.notebook;

import anshay.notebook.common.bo.ListNode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author machao
 * @date 2020/10/19
 */
public class Test {
    public int[][] kClosest(int[][] points, int K) {


        Arrays.sort(points, (v1, v2) -> v1[0] * v1[0] - v2[0] * v2[0] + v1[1] * v1[1] - v2[1] * v2[1]);
        return Arrays.copyOf(points, K);
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        //第一步：将arr1中数据分配到各桶中
        int[] bucket = new int[1001];
        int[] res = new int[arr1.length];
        for (int data : arr1)
            bucket[data]++;
        //第二步：按照arr2的顺序收集桶中数据
        int j = 0;
        for (int data : arr2) {
            while (bucket[data]-- > 0)
                res[j++] = data;
        }
        //第三步：按升序顺序收集桶中其他数据
        for (int i = 0; i < 1001; i++) {
            while (bucket[i]-- > 0)
                res[j++] = i;
        }
        return res;
        // this.();

    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1, head);
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                //满足顺序的，继续往后
                lastSorted = lastSorted.next;
            } else {
                //不满足顺序，从第一个开始找位置
                ListNode pre = dummyHead;
                while (pre.next.val <= curr.val) {
                    pre = pre.next;
                }
                //将断点后的接到之前的排序位置
                lastSorted.next = curr.next;
                curr.next = pre.next;
                pre.next = curr;

            }
            curr = lastSorted.next;

        }
        return dummyHead.next;
    }

    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < nums.length) {
            //初始化数据
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                //这里左窗口滑动
                //对数值做数量判断
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                //窗口左端右滑动
                left++;
            }
            right++;
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    // public void merge(int[] nums1, int m, int[] nums2, int n) {
    //     for (int i = m - 1, j = n - 1, k = m + n - 1; k >= 0 && j >= 0; k--) {
    //         if (i >= 0 && nums1[i] > nums2[j]) {
    //             nums1[k] = nums1[i--];
    //         } else {
    //             nums1[k] = nums2[j--];
    //         }
    //     }
    // }

    //
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m - 1, j = n - 1, k = m + n - 1; k >= 0 && j >= 0; k--) {
            if (i > 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i--];
            } else {
                nums1[k] = nums2[j--];
            }
        }
    }

    // public String findLongestWord(String s, List<String> dictionary) {
    //     String longestStr = "";
    //     for (String item : dictionary) {
    //         if (longestStr.length() > item.length()
    //                 || (longestStr.length() == item.length() && longestStr.compareTo(item) < 0)) {
    //             continue;
    //         }
    //         if (isSubStr(s, item)) {
    //             longestStr = item;
    //         }
    //     }
    //     return longestStr;
    // }
    //
    //
    // public boolean isSubStr(String s, String t) {
    //     int i = 0, j = 0;
    //     //两个字符较长时，要比较偏移量和两字符长度差
    //     while (i < s.length() && j < t.length()) {
    //         if (s.charAt(i) == t.charAt(j)) {
    //             j++;
    //         }
    //         i++;
    //     }
    //     return j == t.length();
    // }

    public String findLongestWord(String s, List<String> dictionary) {
        String longestStr = "";
        for (String item : dictionary) {
            if (longestStr.length() >= item.length()) {
                continue;
            }
            int i = 0, j = 0;
            while (i < s.length() && j < item.length()) {
                if (s.charAt(i) == item.charAt(j)) {
                    j++;
                }
                i++;
            }
            if (j == item.length()) {
                longestStr = item;
            }
        }
        return longestStr;
    }

    public static void main(String[] args) {
        HashMap map = new HashMap();
        ConcurrentHashMap cmap = null;
        List a = new ArrayList();
        LinkedList b = new LinkedList();
        Vector c = new Vector();
    }
}
