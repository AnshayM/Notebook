package pers.anshay.notebook.learn.stackandqueen;

/**
 * @author: Anshay
 * @date: 2019/4/2
 */
public class MyCircularQueue {
    /*数据*/
    private int[] data;

    /*元素数量*/
    private int num = 0;

    /*队首指针*/
    private int front = 0;

    /*队尾指针*/
    private int rear = -1;

    public MyCircularQueue(int k) {
        if (k > 0) {
            data = new int[k];
        }
    }

    /*首先需要判断队列是否已满*/
    public boolean enQueue(int value) {
        if (!isFull()) {
            rear = (rear + 1) % data.length;
            data[rear] = value;
            num++;

            return true;
        }
        return false;
    }

    /*首先需要判断队列是否为空*/
    public boolean deQueue() {
        if (!isEmpty()) {
            front = (front + 1) % data.length;
            num--;
            return true;
        }
        return false;
    }

    /*先判断队列是否为空，若不为空则返回队首指针指向元素*/
    public int Front() {
        if (!isEmpty()) {
            return data[front];
        }
        return -1;
    }

    /*先判断队列是否为空，若不为空则返回队尾指针指向元素*/
    public int Rear() {
        if (!isEmpty()) {
            return data[rear];
        }
        return -1;
    }

    public boolean isEmpty() {
        return num == 0;
    }

    public boolean isFull() {
        return num == data.length;
    }
}
