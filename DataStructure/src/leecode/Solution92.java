package leecode;

import temp.Solution109;
import util.ListNode;

import java.util.Stack;

/**
 * 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 思路：
 * 1先设置一个前驱节pre用来返回pre.next.
 * 2遍历listnode到位置为m的node
 * 3用栈讲m到n这一段存起来，游标节点指到m+1位置(为null时依然为null)（用数组的话可以只改值，但是m-n这一段需要再遍历一次。）
 * 4将stack中节点依次放到m-1位置节点后
 * 5将m+1位置节点衔接上去
 * <p>
 * 注意：节点挪动时，A-->B-->C,调用A.next和直接调用B是不一样的，有可能改变B的结构。所以需要改动哪个节点的信息，就调用哪个
 *
 * @author: Anshay
 * @date: 2019/5/10
 */
public class Solution92 {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        ListNode node2 = reverseBetween(node, 2, 3);
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m < 0 || n <= 0 || m > n) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode pre = new ListNode(0);
        pre.next = head;
        // 当前游标节点
        ListNode curNode = pre;
        int i = 1;
        while (i < m) {
            curNode = curNode.next;
            i++;
        }

        // 到第n-1个节点(0则是前驱节点)
        head = curNode;
        while (i <= n && curNode != null) {
            curNode = curNode.next;
            stack.add(new ListNode(curNode.val));
            i++;
        }
        while (!stack.isEmpty()) {
            head.next = stack.pop();
            head = head.next;
        }
        //衔接后面的（此时的curNode是第n个节点）
        head.next = curNode.next;

        return pre.next;
    }

    /*更快速的解法*/
    public ListNode reverseBetween1(ListNode head, int m, int n) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode startNode = dummyNode;
        n -= m;
        m--;
        // 找到要更改的起始位置的上一个
        while (m != 0) {
            startNode = startNode.next;
            m--;
        }

        ListNode curNode = startNode.next;

        //相当于冒泡方法，一次一次把后面的接到startcode。参见反转一个单链表Solution6
        while (n != 0) {
            ListNode tempNode = startNode.next;
            startNode.next = curNode.next;
            curNode.next = curNode.next.next;
            startNode.next.next = tempNode;

            n--;
        }

        return dummyNode.next;
    }

}
