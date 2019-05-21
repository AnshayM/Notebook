package linkedlist;

import util.ListNode;

/**
 * 两数相加
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @author: Anshay
 * @date: 2019/5/21
 */
public class Solution9 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(3);
        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(6);
        node2.next.next = new ListNode(4);
        addTwoNumbers(node1, node2);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l1.val == 0 && l1.next == null) {
            return l2;
        }
        if (l2 == null || l2.val == 0 && l2.next == null) {
            return l1;
        }
        int carry = 0;
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        while (l1 != null || l2 != null || carry > 0) {
            cur.next = new ListNode(0);
            cur = cur.next;

            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            cur.val = (val1 + val2 + carry) % 10;
            carry = (val1 + val2 + carry) / 10;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        return pre.next;
    }
}
