package linkedlist;

import util.ListNode;

/**
 * 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 1:改变位置
 * 2：改变值
 * <p>
 * 审题！！！其实指的是坐标的奇偶
 *
 * @author: Anshay
 * @date: 2019/5/17
 */
public class Solution7 {
    public static void main(String[] args) {
        ListNode node = new ListNode(2);
        node.next = new ListNode(1);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(5);
        node.next.next.next.next = new ListNode(6);
        node.next.next.next.next.next = new ListNode(4);
        node.next.next.next.next.next.next = new ListNode(7);
        oddEvenList(node);
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode odd = pre.next;
        ListNode evenStart = pre.next.next;
        ListNode evenEnd = evenStart;
        while (odd.next != null && evenEnd.next != null) {
            odd.next = evenEnd.next;
            odd = odd.next;
            evenEnd.next = odd.next;
            evenEnd = evenEnd.next;
        }
        odd.next = evenStart;
        return pre.next;
    }
}
