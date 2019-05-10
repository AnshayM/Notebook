package leecode;

import util.ListNode;

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

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : (l2 != null ? l2 : null);
        }
        //确定l1是较小的那个
        if (l1.val > l2.val) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        // 把 list2 一个一个插入到list1中，所以比判断temp2是否为空，temp1.next是否为空
        while (temp2 != null && temp1.next != null) {
            if (temp2.val < temp1.next.val) {
                //存储下一个节点
                ListNode item1 = temp1.next;
                // 创建新节点item2,准备把新节点加到l1中
                ListNode item2 = new ListNode(temp2.val);
                // 设置此当前节点的next为新节点
                temp1.next = item2;
                // 新节点的next为下一个节点
                item2.next = item1;
                // 下一次比较还是要和item1比较，新加了节点所以list1要后挪一位
                temp1 = temp1.next;
                // list2后挪一位
                temp2 = temp2.next;
            } else {
                // 不满足条件，list1往后挪一位
                temp1 = temp1.next;
            }
        }
        // 如果temp2为空了，则会跳出来，所以能走到这里来的情况就是temp1为空了，但是temp2还有值且都比最后一个temp1大，直接装在后面即可
        if (temp1.next == null) {
            temp1.next = temp2;
        }
        return l1;
    }

}


