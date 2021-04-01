package com.example.thinking.test.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author：zzh
 * @Description: 两个线程交替
 * @Date: 2021/3/31 6:08 下午
 */
public class AutomicPrint3 {

    private static volatile boolean flag = false;

    private static AtomicInteger MAX = new AtomicInteger(1);

    public static void main(String[] args) {

        Thread t1 = new Thread(new Printer1());
        Thread t2 = new Thread(new Printer2());
        t1.start();
        t2.start();

    }

    static class Printer1 implements Runnable {

        @Override
        public void run() {
            while (MAX.get() <= 100) {

                if (flag) {
                    System.out.println(Thread.currentThread().getName() + "线程打印:" + MAX.getAndIncrement());
                    flag = !flag;
                }
            }

        }
    }

    static class Printer2 implements Runnable {

        @Override
        public void run() {
            while (MAX.get() < 100) {

                if (!flag) {
                    System.out.println(Thread.currentThread().getName() + "线程打印:" + MAX.getAndIncrement());
                    flag = !flag;
                }
            }
        }
    }

}

