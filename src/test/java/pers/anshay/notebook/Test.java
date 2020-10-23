package pers.anshay.notebook;

import anshay.notebook.common.pojo.ListNode;
import anshay.notebook.common.util.ListNodeUtil;

/**
 * @author machao
 * @date 2020/10/19
 */
public class Test {
    public boolean isPalindrome(ListNode head) {
        //1。找到中间
        //2。将后面反序
        //3。进行比较
        ListNode middle = getMiddle(head);
        ListNode l2 = middle.next;
        if (l2 == null) {
            return true;
        }
        l2 = reverse(middle);
        return ListNodeUtil.diffListNode(head, l2);
    }



    ListNode getMiddle(ListNode node) {
        ListNode fast = node;
        ListNode slow = node;
        while (fast != null && fast.next != null) {
            fast = node.next.next;
            slow = slow.next;
        }
        return slow;
    }

    ListNode reverse(ListNode node) {
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

}
