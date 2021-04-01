package com.example.thinking.test.algorithm.queue;

public class ArrayQueue {

    // 数组：items，数组大小：n
    private String[] items;
    private int n = 0;

    // head表示队头下标，tail表示队尾下标
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    public String[] getItems() {
        return items;
    }

    // 入队，从tail进入
    public boolean enqueue(String item) {
        // 如果tail == n 表示队列已经满了
        if (tail == n) {
            // tail ==n && head==0，表示整个队列都占满了
            if (head == 0) {
                return false;
            }
            // 数据搬移
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }
            // 搬移完之后重新更新head和tail
            tail -= head;
            head = 0;
            return true;
        }

        items[tail] = item;
        ++tail;
        return true;
    }

    // 出队，从head出去
    public String dequeue() {
        // 如果head == tail 表示队列为空
        if (tail == head) {
            return null;
        }
        String res = items[head];
        head++;
        return res;
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayQueue arrayQueue = new ArrayQueue(50);

        Thread t = new Thread(new Producter(arrayQueue));
        t.start();

        Thread.sleep(1000);

        Thread w = new Thread(new Worker1(arrayQueue));
        w.start();
        //Thread w2 = new Thread(new Worker2(arrayQueue));
        //w2.start();

    }

}


class Producter implements Runnable {

    private ArrayQueue arrayQueue;

    private String item;

    public Producter(ArrayQueue arrayQueue) {
        this.arrayQueue = arrayQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i < 60; i++) {
            boolean result = arrayQueue.enqueue(i + "");
            System.out.println(Thread.currentThread().getName() + "线程，生成元素：" + i + "，返回结果：" + result);
            if (i % 50 == 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + "线程,休息10秒");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

class Worker1 implements Runnable {

    private ArrayQueue arrayQueue;

    public Worker1(ArrayQueue arrayQueue) {
        this.arrayQueue = arrayQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            String item = arrayQueue.dequeue();
            System.out.println(Thread.currentThread().getName() + "线程，获取元素：" + item);
        }
    }

}

class Worker2 implements Runnable {

    private ArrayQueue arrayQueue;

    public Worker2(ArrayQueue arrayQueue) {
        this.arrayQueue = arrayQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            String item = arrayQueue.dequeue();
            System.out.println(Thread.currentThread().getName() + "线程，获取元素：" + item);
        }

    }
}

