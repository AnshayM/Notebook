package anshay.notebook.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 复制带随机指针的链表
 * <p>
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 要求返回这个链表的深拷贝。
 * <p>
 * 思路：
 * 1：用hashMap做映射
 * 2：扩展到当前链表，1-2-3变成1-1-2-2-3-3，然后分割
 *
 * @author: Anshay
 * @date: 2019/5/22
 */
public class Solution11 {
    /*原地扩展*/
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;

        while (cur != null) {
            Node clone = new Node(cur.val, cur.next, null);
            cur.next = clone;
            cur = cur.next.next;
        }
        /*处理然random指针*/
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
        /*还原原始链表，分离克隆链表*/
        cur = head;
        Node res = head.next;
        while (cur.next != null) {
            Node temp = cur.next;
            cur.next = cur.next.next;
            cur = temp;
        }
        return res;
    }

    /*map方法，空间复杂度O(n)*/
    public Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;

        Map<Node, Node> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new Node(cur.val, null, null));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    private class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }
}
