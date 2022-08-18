package pers.anshay.notebook.algorithm.leetcode.old;

import pers.anshay.notebook.common.bo.ListNode;

/**
 * 移除链表元素
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 思路：新建一个头放在head前面，用于存储这个链表，然后遍历head，有同则去除
 *
 * @author: Anshay
 * @date: 2019/5/5
 */
public class Solution203 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode header = new ListNode(-1);
        header.next = head;
        ListNode cur = header;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return header.next;
    }

}
