package pers.anshay.notebook.algorithm.leetcode;

import pers.anshay.notebook.common.bo.ListNode;

/**
 * 83. 删除排序链表中的重复元素
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * 返回同样按升序排列的结果链表。
 *
 * @author machao
 * @date 2021/3/26
 */
public class Solution83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = new ListNode(-1, head);
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        //其实这里可以省一步，直接返回head，但是为了统一处理链表类题目，还是通过pre.next方式返回
        return pre.next;
    }
}
