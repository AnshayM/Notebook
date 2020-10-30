package anshay.notebook.leetcode;

import anshay.notebook.common.pojo.ListNode;

import java.util.Stack;

/**
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 *
 * @author machao
 * @date 2020/10/18
 */
public class Solution19 {
    public static void main(String[] args) {

    }

    /**
     * 用栈求解，不满足一次执行
     *
     * @param head
     * @param n
     * @return
     */
    @Deprecated
    public ListNode removeNthFromEndOld(ListNode head, int n) {
        //记录head的位置
        ListNode beforeHead = new ListNode(-1, head);
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = beforeHead;
        //最后一个没有加到栈里
        while (cur.next != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //回到倒数第（N+1）个
        for (int i = 0; i < n; i++) {
            cur = stack.pop();
        }
        cur.next = cur.next.next;


        return beforeHead.next;
    }

    /**
     * 用两个指针，相差n个位置
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //记录head的位置
        ListNode beforeHead = new ListNode(-1, head);
        ListNode targetBefore = beforeHead;//目标值前一个
        ListNode cur = beforeHead;
        //相差次数
        int time = 0;
        while (cur != null) {
            cur = cur.next;
            if (time >= n + 1) {
                targetBefore = targetBefore.next;
            }
            time++;
        }
        targetBefore.next = targetBefore.next.next;
        return beforeHead.next;
    }


}
