package anshay.notebook.hashtable;

/**
 * 设计哈希映射
 *
 * @author: Anshay
 * @date: 2019/5/20
 */
public class MyHashMap {
    /**
     *
     */
    Node[] arr;
    int capacity;
    int size;
    private static final double THERESHOD = 0.75;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        capacity = 200000;
        arr = new Node[capacity];
        size = 0;
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        put(arr, key, value);
    }

    private void put(Node[] arr, int key, int value) {
        if (size > capacity * THERESHOD) {
            // 二倍扩容
            growCapacity();
        }
        int idx = hash(key) % capacity;
        // 使用二次hash 解决碰撞
        while (arr[idx] != null && arr[idx].key != key) {
            if (arr[idx].value == -1) {
                // 说明这个元素已经被remove了
                break;
            }
            idx = hash(idx) % capacity;
        }
        arr[idx] = new Node(key, value);
        size++;
    }

    private void growCapacity() {
        // 倍增后reHash放入即可
        capacity *= 2;
        Node[] newArr = new Node[capacity];
        reHash(newArr, arr);
        arr = newArr;
    }

    private void reHash(Node[] newArr, Node[] arr) {
        for (Node node : arr) {
            // 被删掉的应该被清除
            if (node != null && node.value != -1) {
                put(newArr, node.key, node.value);
            }
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int idx = getIdxByKey(key);
        return idx == -1 ? -1 : arr[idx].value;
    }

    private int getIdxByKey(int key) {
        int idx = hash(key) % capacity;
        while (arr[idx] != null && arr[idx].key != key) {
            idx = hash(idx) % capacity;
        }
        if (arr[idx] == null || arr[idx].value == -1) {
            return -1;
        }
        return idx;
    }

    private int hash(int key) {
        return Integer.hashCode(key);
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int idx = getIdxByKey(key);
        if (idx != -1) {
            arr[idx].value = -1;
            size--;
        }
    }
}

class Node {
    int key;
    int value;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
