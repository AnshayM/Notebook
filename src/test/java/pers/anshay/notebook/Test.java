package pers.anshay.notebook;

import anshay.notebook.common.pojo.ListNode;

/**
 * @author machao
 * @date 2020/10/19
 */
public class Test {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        boolean palindrome = isPalindrome(head);

    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        //1。找到中间
        //2。将后面反序
        //3。进行比较
        ListNode middle = getMiddle(head);
        ListNode l2 = middle.next;
        middle.next = null;
        if (l2 == null) {
            return true;
        }
        l2 = reverse(l2);
        return diffListNode(head, l2);
    }


    static ListNode getMiddle(ListNode node) {
        ListNode fast = node;
        ListNode slow = node;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    static ListNode reverse(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

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
