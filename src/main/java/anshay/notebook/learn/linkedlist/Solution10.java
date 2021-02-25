package anshay.notebook.learn.linkedlist;

import java.util.Stack;

/**
 * 扁平化多级双向链表
 * <p>
 * 您将获得一个双向链表，除了下一个和前一个指针之外，它还有一个子指针，可能指向单独的双向链表。
 * 这些子列表可能有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * 扁平化列表，使所有结点出现在单级双链表中。您将获得列表第一级的头部。
 *
 * @author: Anshay
 * @date: 2019/5/21
 */
public class Solution10 {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null && head.child == null) {
            return head;
        }

        Node cur = head;
        Stack<Node> stack = new Stack<>();
        while (cur != null && (cur.next != null || cur.child != null || stack.size() > 0)) {
            if (cur.child != null) {
                if (cur.next != null) {
                    stack.push(cur.next);
                }
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
            } else if (cur.child == null && !stack.isEmpty()) {
                Node next = stack.pop();
                cur.next = next;
                next.prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    private class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {
        }

        public Node(int _val, Node _prev, Node _next, Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

}
