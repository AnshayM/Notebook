package anshay.notebook.learn.hashtable;

import java.util.*;

/**
 * @author: Anshay
 * @date: 2019/5/27
 */
public class RandomizedSet {
    List<Integer> vals;
    Map<Integer, Integer> locals;
    Random random = new Random();

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        vals = new ArrayList<Integer>();
        locals = new HashMap<Integer, Integer>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (locals.containsKey(val)) {
            return false;
        }
        locals.put(val, vals.size());
        vals.add(val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (locals.containsKey(val)) {
            int loc = locals.get(val);
            int size = vals.size() - 1;
            if (loc < size) {
                int lastVal = vals.get(size);
                vals.set(loc, lastVal);
                locals.put(lastVal, loc);
            }
            locals.remove(val);
            vals.remove(size);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return vals.get(random.nextInt(vals.size()));
    }
}
