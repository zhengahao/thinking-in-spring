package com.example.thinking.test.algorithm;

/**
 * 循环队列
 */
public class CircularQueue {

    // 数组：items，数组大小：n
    private String[] items;

    private int n = 0;

    // head表示队头下标，tail表示队尾下标
    private int head;

    private int tail;

    // 申请一个大小为capacity的数组
    public CircularQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    public boolean enqueue(String item) {
        // 队列满了
        if ((tail + 1) % n == head) {
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    public String dequeue() {
        // 如果head == tail 表示队列为空
        if (head == tail) {
            return null;
        }
        String ret = items[head];
        head = (head + 1) % n;
        return ret;
    }

    public static void main(String[] args) throws InterruptedException {
        CircularQueue circularQueue = new CircularQueue(101);

        for (int i = 0; i < 100; i++) {
            circularQueue.enqueue(i + "");
        }

        Thread.sleep(1000);

        for (int i = 0; i < 100; i++) {
            System.out.println(circularQueue.dequeue());
        }
    }


}
