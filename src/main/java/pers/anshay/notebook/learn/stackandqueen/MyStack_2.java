package pers.anshay.notebook.learn.stackandqueen;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 *
 * @author: Anshay
 * @date: 2019/4/27
 */
public class MyStack_2 {
    Queue<Integer> queue;
    Queue<Integer> tempQueue;

    /**
     * Initialize your data structure here.
     */
    public MyStack_2() {
        queue = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        tempQueue = new LinkedList<>();
        tempQueue.offer(x);
        while (!queue.isEmpty()) {
            tempQueue.offer(queue.poll());
        }
        queue = tempQueue;
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return queue.poll();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return queue.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}
