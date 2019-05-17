package linkedlist;

/**
 * @author: Anshay
 * @date: 2019/5/17
 */
public class MyLinkedList2 {
    private Node root;
    private Node rear;
    private int size;

    private class Node {
        int val;
        Node next;

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    /** Initialize your data structure here. */
    public MyLinkedList2() {
        root = new Node(0, null);
        rear = root;
        size = 0;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is
     * invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
		/*if(index == 0) {
            return root.next.val;
        }
		if (index == size - 1) {
			return rear.val;
		}*/
        Node node = root;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.next.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After
     * the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node node = new Node(val, root.next);
        if (root.next == null) {
            rear = node;
        }
        root.next = node;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node = new Node(val, null);
        rear.next = node;
        rear = node;
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index
     * equals to the length of linked list, the node will be appended to the end of
     * linked list. If index is greater than the length, the node will not be
     * inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
		/*if (index <= 0) {
			addAtHead(val);
			return;
		}*/
        if (index == size) {
            addAtTail(val);
            return;
        }
        Node node = root;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        Node newNode = new Node(val, node.next);
        node.next = newNode;
        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        Node node = root;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        node.next = node.next.next;
        if (index == size - 1) {
            if (node.next != null) {
                rear = node.next;
            } else {
                rear = node;
            }
        }
        size--;
    }
}
