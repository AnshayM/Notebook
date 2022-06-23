package pers.anshay.notebook.learn.stackandqueen;

import java.util.ArrayList;
import java.util.List;

/**
 * 自己实现一个栈
 *
 * @author: Anshay
 * @date: 2019/4/2
 */
public class MyStack {
    private List<Integer> data;

    public MyStack() {
        data = new ArrayList<>();
    }

    public void push(int x) {
        data.add(x);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int top() {
        return data.get(data.size() - 1);
    }

    public boolean pop() {
        if (isEmpty()) {
            return false;
        }
        data.remove(data.size() - 1);
        return true;
    }
}
