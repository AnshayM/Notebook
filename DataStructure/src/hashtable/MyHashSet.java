package hashtable;

import java.util.HashMap;

/**
 * 设计哈希集合
 *
 * @author: Anshay
 * @date: 2019/5/20
 */
public class MyHashSet {
    boolean[] set;
    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {
        set = new boolean[1000000];
    }

    public void add(int key) {
        set[key] = true;
    }

    public void remove(int key) {
        set[key] = false;
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        return set[key];
    }
}
