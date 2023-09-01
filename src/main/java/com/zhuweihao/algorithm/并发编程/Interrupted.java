package com.zhuweihao.algorithm.并发编程;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhuweihao
 * @Date 2023/9/1 16:18
 * @Description com.zhuweihao.algorithm.并发编程
 */
public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        //sleepThread
        Thread sleepThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "SleepThread");
        //busyThread
        Thread busyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                }
            }
        }, "BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();

        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());

        TimeUnit.SECONDS.sleep(3);
    }
}
