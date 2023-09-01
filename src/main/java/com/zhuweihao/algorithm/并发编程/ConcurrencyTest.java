package com.zhuweihao.algorithm.并发编程;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zhuweihao
 * @Date 2023/8/20 12:45
 * @Description com.zhuweihao.algorithm.并发编程
 */
public class ConcurrencyTest {
    private static final long count = 100000000L;

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue<>();
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.compareAndSet(1, 1);
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrency:" + time + "ms,b=" + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }
}
