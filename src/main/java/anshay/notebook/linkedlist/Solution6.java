package anshay.notebook.linkedlist;

import anshay.notebook.util.ListNode;

/**
 * 反转一个单链表。
 *
 * @author: Anshay
 * @date: 2019/5/17
 */
public class Solution6 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = new ListNode(0);
        ListNode temp;
        pre.next = head;
        ListNode cur = head;
        while (cur.next != null) {
            // 要被挪动到第二位的节点
            temp = pre.next;
            // 挪动指定节点到第一位
            pre.next = cur.next;
            // cur后删除已经挪动的节点
            cur.next = cur.next.next;
            // 连接第一位和第二位
            pre.next.next = temp;
        }
        return pre.next;
    }
}
