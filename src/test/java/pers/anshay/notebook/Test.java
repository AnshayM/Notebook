package pers.anshay.notebook;

import anshay.notebook.common.pojo.ListNode;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author machao
 * @date 2020/10/19
 */
public class Test {
    public int[][] kClosest(int[][] points, int K) {


        Arrays.sort(points, (v1, v2) -> v1[0] * v1[0] - v2[0] * v2[0] + v1[1] * v1[1] - v2[1] * v2[1]);
        return Arrays.copyOf(points, K);
    }

    public static void main(String[] args) {
        ConcurrentHashMap hashMap = new ConcurrentHashMap();
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = new ListNode(-1, head);
        ListNode node1 = head;
        ListNode node2 = head.next;
        ListNode pre2 = new ListNode(-1, node2);
        while (node1.next != null && node2.next != null) {
            node1.next = node2.next;
            node1 = node1.next;
            node2.next = node1.next;
            node2 = node2.next;
        }
        node1.next = pre2.next;
        return pre.next;
    }

    public static ListNode oddEvenList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = new ListNode(-1, head);//奇数pre
        ListNode node1 = head;//奇数当前
        ListNode node2 = head.next;//偶数当前
        ListNode pre2 = new ListNode(-1, node2);//偶数pre
        while (node1.next != null && node2.next != null) {
            node1.next = node2.next;//隔一个偶后就是下一个奇
            node1 = node1.next;//移动指针
            node2.next = node1.next;//已经挪到一下的奇的后一个就是下一个偶
            node2 = node2.next;//移动指针
        }
        node1.next = pre2.next;
        return pre.next;
    }

}
