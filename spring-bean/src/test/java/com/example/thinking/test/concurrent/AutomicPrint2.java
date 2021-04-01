package com.example.thinking.test.concurrent;

/**
 * @Author：zzh
 * @Description: 两个线程交替
 * @Date: 2021/3/31 6:08 下午
 */
public class AutomicPrint2 {

    private static Object lock = new Object();

    private static volatile int MAX = 0;

    public static void main(String[] args) {

        Thread t1 = new Thread(new Printer1());
        Thread t2 = new Thread(new Printer2());
        t1.start();
        t2.start();

    }

    static class Printer1 implements Runnable {

        @Override
        public void run() {
            while (MAX < 100) {

                synchronized (lock) {
                    if (MAX % 2 == 0) {
                        MAX++;
                        System.out.println(Thread.currentThread().getName() + "线程打印:" + MAX);
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

        }
    }

    static class Printer2 implements Runnable {

        @Override
        public void run() {
            while (MAX < 100) {
                synchronized (lock) {
                    if (MAX % 2 != 0) {
                        MAX++;
                        System.out.println(Thread.currentThread().getName() + "线程打印:" + MAX);
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}

