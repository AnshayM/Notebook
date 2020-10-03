package anshay.notebook.hashtable;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 常数时间插入、删除和获取随机元素
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
 * insert(val)：当元素 val 不存在时，向集合中插入该项。
 * remove(val)：元素 val 存在时，从集合中移除该项。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 *
 * @author: Anshay
 * @date: 2019/5/27
 */
public class RandomizedSet1 {
    Set<Integer> set;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet1() {
        set = new HashSet<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        return set.add(val);
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        return set.remove(val);
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int index = new Random().nextInt(set.size());
        int res = -1;
        for (int item : set) {
            if (index-- == 0) {
                res = item;
                break;
            }
        }
        return res;
    }

}
