package linkedlist;

import util.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 *
 * @author: Anshay
 * @date: 2019/5/17
 */
public class Solution3 {

    /*
     * 判断是否有环，然后找入环的第一个节点。
     * 具体方法：首先假定链表起点到入环的第一个节点A的长度为a【未知】，到快慢指针相遇的节点B的长度为（a + b）【这个长度是已知的】。
     * 现在我们想知道a的值，注意到快指针p2始终是慢指针p走过长度的2倍，所以慢指针p从B继续走（a + b）又能回到B点，如果只走a个长度就能回到节点A。
     * 但是a的值是不知道的，解决思路是曲线救国，注意到起点到A的长度是a，那么可以用一个从起点开始的新指针q和从节点B开始的慢指针p同步走，
     * 相遇的地方必然是入环的第一个节点A。
     *
     * slow所在位置是a+b，再走a+b还是到原来的位置。但是走一个a的距离肯定是到入环节点，这个时候与同时从head出发的新fast相遇，所以以相遇为判断条件。
     *
     * */
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /*时间空间消耗都很大*/
    public  ListNode detectCycle1(ListNode head) {
        Set<ListNode> nodes = new HashSet<>();
        while (head != null) {
            if (!nodes.add(head)) {
                return head;
            }
            head = head.next;
        }
        return null;
    }
}
