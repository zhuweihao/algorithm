package com.zhuweihao.algorithm.并发编程;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhuweihao
 * @Date 2023/9/1 16:31
 * @Description com.zhuweihao.algorithm.并发编程
 */
public class ShutDown {

    private static class Runner implements Runnable {
        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void concel() {
            on = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread thread = new Thread(one, "one");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        Runner two = new Runner();
        thread = new Thread(two, "two");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        two.concel();
    }
}
