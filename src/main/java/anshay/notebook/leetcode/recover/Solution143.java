package anshay.notebook.leetcode.recover;

import anshay.notebook.common.pojo.ListNode;
import anshay.notebook.common.util.ListNodeUtil;

/**
 * 143. 重排链表
 * 给定一个单链表L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例1:
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
        ListNode middle = ListNodeUtil.getMiddle(head);
        ListNode l2 = middle.next;
        l2 = ListNodeUtil.reverseListNode(l2);
        middle.next = null;
        ListNodeUtil.mergerList(head, l2);
    }

}
