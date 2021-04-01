package com.example.thinking.test.concurrent;

/**
 * @Author：zzh
 * @Description: https://juejin.cn/post/6844903782199853069#heading-3
 * @Date: 2021/3/31 6:08 下午
 */
public class AutomicPrint {

    private static volatile int MAX = 0;

    public static void main(String[] args) {

        Thread t1 = new Thread(new Thread1());
        Thread t2 = new Thread(new Thread2());
        t1.start();
        t2.start();

    }

    static class Thread1 implements Runnable {

        @Override
        public void run() {
            while (MAX <= 100) {
                if (MAX % 2 == 0) {
                    synchronized (AutomicPrint.class) {
                        MAX++;
                        System.out.println(Thread.currentThread().getName() + "线程打印:" + MAX);
                    }
                }
            }

        }
    }

    static class Thread2 implements Runnable {

        @Override
        public void run() {
            while (MAX < 100) {
                if (MAX % 2 != 0) {
                    synchronized (AutomicPrint.class) {
                        MAX++;
                        System.out.println(Thread.currentThread().getName() + "线程打印:" + MAX);
                    }
                }
            }
        }
    }

}

