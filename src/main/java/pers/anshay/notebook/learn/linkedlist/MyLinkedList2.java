package pers.anshay.notebook.learn.linkedlist;

/**
 * 设计链表(双链表)
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。
 * 如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 * <p>
 * 在链表类中实现这些功能：
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。
 * 如果 index 大于链表长度，则不会插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 *
 * @author: Anshay
 * @date: 2019/5/17
 */
class MyLinkedList2 {

    private int count;
    private Node head;
    private Node tail;

    private static class Node {
        int val;
        Node next;
        Node prev;

        Node(int val) {
            this.val = val;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList2() {
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index >= 0 && index < count) {
            Node result = head;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
            return result.val;
        } else {
            return -1;
        }
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node node = new Node(val);
        if (count == 0) {
            head = node;
            tail = node;
        } else {
            Node temp = head;
            node.next = temp;
            temp.prev = node;
            head = node;
        }
        count++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node node = new Node(val);
        if (count == 0) {
            head = node;
            tail = node;
        } else {
            Node temp = tail;
            temp.next = node;
            node.prev = temp;
            tail = node;
        }
        count++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > count) {
            return;
        }
        if (index == count) {
            addAtTail(val);
        } else if (index <= 0) {
            addAtHead(val);
        } else {
            Node node = new Node(val);
            Node origin = head;
            for (int i = 0; i < index; i++) {
                origin = origin.next;
            }
            Node prev = origin.prev;
            prev.next = node;
            node.prev = prev;
            origin.prev = node;
            node.next = origin;
            count++;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index >= 0 && index < count) {
            if (count == 1) {
                head = tail = null;
            } else {
                Node origin = head;
                for (int i = 0; i < index; i++) {
                    origin = origin.next;
                }
                if (origin.prev == null) {
                    head = origin.next;
                    origin.next.prev = null;
                } else if (origin.next == null) {
                    tail = origin.prev;
                    origin.prev.next = null;
                } else {
                    origin.prev.next = origin.next;
                    origin.next.prev = origin.prev;
                }
            }
            count--;
        }
    }
}
