package anshay.notebook.leecode;

import anshay.notebook.util.ListNode;

/**
 * 143. 重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * <p>
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * <p>
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author machao
 * @date 2020/10/20
 */
public class Solution143 {
    //思路一，用List转换，带上下标，如果没有其他要求，可以处理


    //思路二，
    //1，快慢指针找中间节点
    //2，反转右端子链表
    //3，两个链表合并
    //我自己想的是使用栈做操作，这样麻烦了
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode middle = getMiddle(head);
        ListNode l2 = middle.next;
        l2 = reverseListNode(l2);
        middle.next = null;
        mergerList(head, l2);
    }

    public ListNode getMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
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
    public ListNode reverseListNode(ListNode head) {
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
    void mergerList(ListNode l1, ListNode l2) {
        ListNode temp1;
        ListNode temp2;
        while (l1 != null && l2 != null) {
            temp1 = l1.next;
            temp2 = l2.next;
            l1.next = l2;
            l2.next = temp1;
            l1 = temp1;
            l2 = temp2;
        }
    }
}
