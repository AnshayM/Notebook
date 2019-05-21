package linkedlist;

import util.ListNode;

/**
 * 旋转链表（待重构）
 * <p>
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 思路：即把前（size- k % size）个节点放到末端去
 *
 * @author: Anshay
 * @date: 2019/5/21
 */
public class Solution12 {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        rotateRight(node, 2);

    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode cur = pre;
        ListNode oldTail = null;
        ListNode newTail = null;
        ListNode newHead = null;
        int size = 0;

        // 获取size和旧尾节点
        while (cur.next != null) {
            size++;
            cur = cur.next;
        }
        oldTail = cur;
        if (k % size == 0) {
            return pre.next;
        }
        k = size - k % size;

        // 获取新尾节点、新头节点
        cur = pre;
        while (k > 0) {
            cur = cur.next;
            k--;
        }
        newTail = cur;
        newHead = cur.next;
        newTail.next = null;
        oldTail.next = head;
        pre.next = newHead;

        return pre.next;
    }

}
