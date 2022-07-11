package pers.anshay.notebook.algorithm.leetcode.core;

import pers.anshay.notebook.common.bo.ListNode;
import pers.anshay.notebook.learn.linkedlist.Solution3;

/**
 * 相交链表
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * 输出相交节点值
 * 思路：
 * 1.将A末端next指向B头。
 * 2.问题变成 环形链表 II 的问题
 *
 * @author: Anshay
 * @date: 2019/5/17
 */
public class Solution160 {
    /**
     * 定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部, 最后如果相遇则为交点(在第一轮移动中恰好抹除了长度差)
     * 两个指针等于移动了相同的距离, 有交点就返回, 无交点就是各走了两条指针的长度
     **/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        /*如果长度相同，第一轮时*/
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    /*这种方法改变了数据结构（之前为null，还原成null，不知道哪里改了）*/
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode fast = headA;
        while (fast.next != null) {
            fast = fast.next;
        }
        fast.next = headB;
        ListNode res = Solution3.detectCycle(headA);
        while (headA.next != headB) {
            headA = headA.next;
        }
        headA.next = null;
        return res;
    }

}
