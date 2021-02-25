package anshay.notebook.learn.linkedlist;

import anshay.notebook.common.util.MyUtil;
import anshay.notebook.common.pojo.ListNode;

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
        MyUtil.print(isPalindrome(node));

    }

    public static boolean isPalindrome(ListNode head) {
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
        //判断链表是奇数个还是偶数个，fast为null表示奇数个，slow的位置就是中心点；
        //fast==null时表示偶数个，slow的下一个才是平分的后半段
        if (fast != null) {
            slow = slow.next;
        }
        //对两段进行比较
        while (slow != null && pre != null) {
            if (slow.val != pre.val) {
                return false;
            }
            slow = slow.next;
            pre = pre.next;
        }
        return true;
    }
}
