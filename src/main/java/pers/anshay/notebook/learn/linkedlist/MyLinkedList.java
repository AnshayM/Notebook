package pers.anshay.notebook.learn.linkedlist;

/**
 * 设计链表(单链表)
 *
 * @author: Anshay
 * @date: 2019/5/16
 */
public class MyLinkedList {
    private int val;
    private MyLinkedList next;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
    }

    public MyLinkedList(int val) {
        this.val = val;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index == 0) {
            return this.val;
        }
        index--;
        MyLinkedList target = this.next;
        while (target != null && index >= 0) {
            if (index == 0) {
                return target.val;
            }
            target = this.next;
            index--;
        }
        return -1;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        MyLinkedList temp = new MyLinkedList();
        temp.next = this.next;
        temp.val = this.val;
        this.val = val;
        this.next = temp;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        MyLinkedList cur = this.next != null ? this.next : new MyLinkedList(val);
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new MyLinkedList(val);
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    /*需要考虑前置节点，有可能是加在头部*/
    public void addAtIndex(int index, int val) {
        MyLinkedList head = new MyLinkedList();
        head.next = this;
        while (head.next != null && index >= 0) {
            if (index == 0) {
                MyLinkedList temp = head.next;
                head.next = new MyLinkedList(val);
                head.next.next = temp;
                return;
            }
            head = head.next;
            index--;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        MyLinkedList head = new MyLinkedList();
        head.next = this;
        while (head.next != null && index >= 0) {
            if (index == 0) {
                head.next = head.next.next;
                return;
            }
            head = head.next;
            index--;
        }
    }
}
