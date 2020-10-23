package anshay.notebook.common.util;

import anshay.notebook.common.pojo.ListNode;

/**
 * @author machao
 * @date 2020/10/23
 */
public class ListNodeUtil {
    public static ListNode getMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 第一步：
     * pre  cur --   --
     * null A   B    C
     * <p>
     * 循环一次后：
     * --  pre -- cur
     * null B   A   C
     * ...
     *
     * @param head
     * @return
     */
    public static ListNode reverseListNode(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            //暂存当前node的下一个
            ListNode temp = cur.next;
            //将前一个节点设置为当前节点的下一个
            cur.next = pre;
            //移动游标，当前节点成为下一轮的上一个节点，前面暂存的节点成为进行下一轮的当前节点
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 合并两个链表
     *
     * @param l1
     * @param l2
     */
    public static void mergerList(ListNode l1, ListNode l2) {
        ListNode temp1 = null;
        ListNode temp2 = null;
        while (l1 != null && l2 != null) {
            temp1 = l1.next;
            temp2 = l2.next;
            l1.next = l2;
            l2.next = temp1;
            l1 = temp1;
            l2 = temp2;
        }
    }

    /**
     * 比较两个链表是否相同前缀
     *
     * @param l1
     * @param l2
     * @return
     */
    public static boolean diffListNode(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }
}
