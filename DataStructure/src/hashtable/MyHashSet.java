package hashtable;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 设计哈希集合
 * <p>
 * 不使用任何内建的哈希表库设计一个哈希集合
 * 具体地说，你的设计应该包含以下的功能
 * add(value)：向哈希集合中插入一个值。
 * contains(value) ：返回哈希集合中是否存在这个值。
 * remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *
 * @author: Anshay
 * @date: 2019/5/20
 */
public class MyHashSet {
    private LinkedList[] lists;
    private final int size = 10000;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {
        lists = new LinkedList[size];
    }

    public void add(int key) {
        int index = key % size;
        if (lists[index] == null) {
            lists[index] = new LinkedList();
        }
        if (!contains(key)) {
            lists[index].addFirst(key);
        }
    }

    public void remove(int key) {
        int index = key % size;
        if (lists[index] != null) {
            lists[index].remove((Integer) key);
        }
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int index = key % size;
        return lists[index] != null && lists[index].contains(key);
    }
}
