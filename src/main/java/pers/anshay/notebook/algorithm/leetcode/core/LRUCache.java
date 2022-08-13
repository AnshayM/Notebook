package pers.anshay.notebook.algorithm.leetcode.core;

import java.util.HashMap;

/**
 * @author machao
 * @date 2022/8/6
 */
public class LRUCache {
    private HashMap<Integer, LinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private LinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = new LinkedNode();
        this.tail = new LinkedNode();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        LinkedNode linkedNode = cache.get(key);
        if (linkedNode == null) {
            return -1;
        }
        movedToHead(linkedNode);
        return linkedNode.value;
    }

    public void put(int key, int value) {
        LinkedNode linkedNode = cache.get(key);
        if (linkedNode != null) {
            linkedNode.value = value;
            movedToHead(linkedNode);
            return;
        }
        linkedNode = new LinkedNode(key, value);
        addToHead(linkedNode);
        size++;
        if (size > capacity) {
            removeTail();
        }
    }

    /**
     * 近期操作过的，挪到后面
     */
    public void movedToHead(LinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    /**
     * 添加到头部
     *
     * @param node node
     */
    public void addToHead(LinkedNode node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;

    }

    /**
     * 容量过大时，移除末尾
     */
    public void removeTail() {
        LinkedNode prev = tail.prev.prev;
        tail.prev = prev;
        prev.next = tail;
        size--;
    }


    class LinkedNode {
        int key;
        int value;
        LinkedNode prev;
        LinkedNode next;

        public LinkedNode() {
        }

        public LinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


}
