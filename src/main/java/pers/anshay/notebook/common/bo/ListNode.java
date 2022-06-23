package pers.anshay.notebook.common.bo;

/**
 * 自定义公用链表节点类
 *
 * @author: Anshay
 * @date: 2019/5/10
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }


    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
        next = null;
    }
}
