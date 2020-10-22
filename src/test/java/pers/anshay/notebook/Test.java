package pers.anshay.notebook;

import anshay.notebook.util.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author machao
 * @date 2020/10/19
 */
public class Test {
    public int removeDuplicates2(int[] nums) {
        int course = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[course + 1] != nums[i] && course + 1 < nums.length) {
                //这里不一样，不相等时将下一位的值设为指定值
                nums[++course] = nums[i];
            }
        }
        //这里是下角标
        return course + 1;
    }

    /**
     * 重排链表
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        ListNode beforeHead = new ListNode(-1, head);
        //思路，分别用快慢指针，遍历一遍找到中间的。用栈录入慢节点
        //以中间为标准点，退栈和往后遍历，做插入操作
        ListNode first = beforeHead;
        ListNode slow = beforeHead;

    }

    public static void main(String[] args) {
        boolean longPressedName = isLongPressedName("vtkgn", "vttkgnn");
        //"vtkgn"
        // "vttkgnn"
    }

    public static boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        //执行完后i是加了1的
        return i == name.length();
    }

    public List<Integer> partitionLabels(String S) {
        //记录每个字母的最后下标
        int[] last = new int[26];
        int length = S.length();
        //这一遍将最后出现的下标记录在了last数组里
        for (int i = 0; i < length; i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        //记录长度列表的结果
        List<Integer> list = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            //更新当前片段的end值
            end = Math.max(end, last[S.charAt(i) - 'a']);
            if (i == end) {
                list.add(end - start + 1);
                start = end + 1;
            }
        }
        return list;
    }
}
