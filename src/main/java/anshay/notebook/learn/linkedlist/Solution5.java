package anshay.notebook.learn.linkedlist;

import anshay.notebook.common.pojo.ListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 说明：
 * 给定的 n 保证是有效的。
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 *
 * @author: Anshay
 * @date: 2019/5/17
 */
public class Solution5 {

    /*用两个指针left和right，相隔n（right要挪n+1次）个，然后挪到尾部，则right是最后一个，left是倒数第n+1个，right是倒数第一个的next(null)*/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode header = new ListNode(0);
        header.next = head;
        ListNode left = header;
        ListNode right = header;
        for (int i = 0; i <= n; i++) {
            right = right.next;
        }
        while (right != null) {
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;
        return header.next;
    }

    /*用hashMap(比队列要好一些)*/
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode header = new ListNode(0);
        header.next = head;
        Map<Integer, ListNode> map = new HashMap<>();
        int index = 0;
        map.put(index, header);
        while (head != null) {
            map.put(++index, head);
            head = head.next;
        }

        ListNode pre = map.get(index - n);
        pre.next = pre.next.next;

        return header.next;
    }

    /*用队列*/
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode header = new ListNode(0);
        header.next = head;
        Stack<ListNode> stack = new Stack<>();
        stack.add(header);
        while (head != null) {
            stack.add(head);
            head = head.next;
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode pre = stack.pop();
        pre.next = pre.next.next;

        return header.next;
    }

}
