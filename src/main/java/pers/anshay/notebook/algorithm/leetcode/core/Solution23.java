package pers.anshay.notebook.algorithm.leetcode.core;

import pers.anshay.notebook.common.bo.ListNode;

/**
 * 合并K个升序链表
 *
 * @author machao
 * @date 2022/8/1
 */
public class Solution23 {
    public static ListNode mergeKLists(ListNode[] list) {
        if (list==null||list.length==0) {
            return null;
        }
        ListNode listNode = list[0];
        if (list.length == 1) {
            return listNode;
        }
        for (int i = 1; i < list.length; i++) {
            listNode= mergeTwoLists(listNode, list[i]);
        }
        return listNode;
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 != null ? list1 : list2;
        }
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
