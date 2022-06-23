package pers.anshay.notebook.learn.stackandqueen;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
 * @author: Anshay
 * @date: 2019/4/2
 */
class MinStack {
    private Deque<Integer> mainStack;
    private Deque<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        mainStack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int x) {
        mainStack.push(x);
        Integer min = minStack.peek();
        // 可能会有多个重复值，所以相等的也要加进来
        if (min == null || min >= x) {
            minStack.push(x);
        }

    }

    public int pop() {
        int cur = mainStack.pop();
        if (cur == minStack.peek()) {
            minStack.pop();
        }
        return cur;
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
