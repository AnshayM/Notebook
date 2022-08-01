package pers.anshay.notebook.algorithm.leetcode.core;

import pers.anshay.notebook.common.bo.ListNode;

/**
 * 反转链表
 *
 * @author machao
 * @date 2022/8/1
 */
public class Solution206 {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            // 记录curr的next
            ListNode next = curr.next;
            // 前面的摆到后面来
            curr.next = pre;
            // 游标往后挪，进行遍历
            pre = curr;
            curr = next;
        }
        return pre;
    }
}
