package anshay.notebook.leetcode.unsolved;

import anshay.notebook.common.pojo.ListNode;

/**
 * 删除链表中的节点
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
 * <p>
 * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
 * <p>
 * (弃用)其实就是把给定的node全部向前挪一位，然后末位删除
 * 直接让给定的node值于下一个相等，然后给定node.next = node.next.next,即复制第二个到第一个然后跳过第二个，实现删除效果
 *
 * @author: Anshay
 * @date: 2019/4/19
 */
public class Solution237 {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }


}


