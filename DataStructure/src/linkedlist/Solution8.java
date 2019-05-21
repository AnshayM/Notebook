package linkedlist;

import util.ListNode;
import util.MyUtil;

/**
 * 回文链表
 * 请判断一个链表是否为回文链表。
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 思路：翻转这个链表，然后一起比较
 *
 * @author: Anshay
 * @date: 2019/5/20
 */
public class Solution8 {
    public static void main(String[] args) {
        ListNode node = new ListNode(-129);
        node.next = new ListNode(-129);
        MyUtil.print(isPalindrome1(node));

    }

    public static boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        /*找中心点,同时翻转前半部分链表存在pre中*/
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;

        }
        if (fast != null) {
            slow = slow.next;
        }
        while (slow != null) {
            if (slow.val != pre.val) {
                return false;
            }
            slow = slow.next;
            pre = pre.next;
        }
        return true;
    }
}
