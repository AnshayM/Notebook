package pers.anshay.notebook.algorithm.double_pointer;

import pers.anshay.notebook.common.bo.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 141 环形链表
 *
 * @author: Anshay
 * @date: 2019/5/17
 */
public class Solution141 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        /*必须是内存地址比较才能确定是同一个节点*/
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }

    public boolean hasCycle1(ListNode head) {
        Set<ListNode> nodes = new HashSet<>();
        while (head != null) {
            if (!nodes.add(head)) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

}
