package pers.anshay.notebook.algorithm.leetcode.core;


import pers.anshay.notebook.common.bo.ListNode;

/**
 * 合并两个有序链表
 * 由小到大
 *
 * @author: Anshay
 * @date: 2019/4/18
 */
public class Solution21 {
    public static void main(String[] args) {
//        ListNode l1 = new ListNode(4, null);
//        l1 = new ListNode(2, l1);
//        l1 = new ListNode(1, l1);
//        ListNode l2 = new ListNode(4, null);
//        l2 = new ListNode(3, l2);
//        l2 = new ListNode(1, l2);
        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode();
        ListNode l3 = mergeTwoLists(null, null);
        ListNode l4 = new ListNode(2);
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 != null ? list1 : list2;
        }
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }
        //确定l1是较小的那个
        if (l1.val > l2.val) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        // 把 list2 一个一个插入到list1中，所以比判断cur2是否为空，cur1.next是否为空
        while (cur2 != null && cur1.next != null) {
            if (cur2.val < cur1.next.val) {
                //存储下一个节点
                ListNode item1 = cur1.next;
                // 创建新节点item2,准备把新节点加到l1中
                cur1.next = new ListNode(cur2.val);
                // 新节点的next为下一个节点
                cur1.next.next = item1;
                // 下一次比较还是要和item1比较，新加了节点所以list1要后挪一位
                cur1 = cur1.next;
                // list2后挪一位
                cur2 = cur2.next;
            } else {
                // 不满足条件，list1往后挪一位
                cur1 = cur1.next;
            }
        }
        // 如果cur2为空了，则会跳出来，所以能走到这里来的情况就是cur1为空了，但是cur2还有值且都比最后一个cur1大，直接装在后面即可
        if (cur1.next == null) {
            cur1.next = cur2;
        }
        return l1;
    }

}


