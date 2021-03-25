package anshay.notebook.leetcode;

import anshay.notebook.common.pojo.ListNode;

/**
 * 删除排序链表中的重复元素 II
 *
 * @author machao
 * @date 2021/3/25
 */
public class Solution82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = new ListNode(-1, head);
        ListNode cur = pre;
        while (cur.next != null && cur.next.next != null) {
            //判断是否重复
            if (cur.next.val == cur.next.next.val) {
                //移除所有重复部分
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return pre.next;
    }
}
