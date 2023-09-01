package com.zhuweihao.algorithm.jvm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zhuweihao
 * @Date 2023/8/18 21:19
 * @Description com.zhuweihao.algorithm.jvm
 */
public class ShareResource {
    // A:1 B:2 C:30
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    // 循环打印5次
    public void print5() {
        // 1、获取锁资源
        lock.lock();
        try {
            // 2、判断是否可以执行业务
            while (num != 1) {
                // 阻塞等待
                conditionA.await();
            }
            // 模拟业务执行
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + (i + 1));
            }
            // 3、通知其他线程，通过signal()方法唤醒线程
            num = 2;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 循环打印10次
    public void print10() {
        // 1、获取锁资源
        lock.lock();
        try {
            // 2、判断是否可以执行业务
            while (num != 2) {
                conditionB.await();
            }
            // 模拟业务执行
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + (i + 1));
            }
            // 3、通知其他线程
            num = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 循环打印15次
    public void print15() {
        // 1、获取锁资源
        lock.lock();
        try {
            // 2、判断是否可以执行业务
            while (num != 3) {
                conditionC.await();
            }
            // 模拟业务执行
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + (i + 1));
            }
            // 3、通知其他线程
            num = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
