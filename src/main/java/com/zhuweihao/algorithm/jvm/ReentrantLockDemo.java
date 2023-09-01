package com.zhuweihao.algorithm.jvm;

/**
 * @Author zhuweihao
 * @Date 2023/8/18 21:20
 * @Description com.zhuweihao.algorithm.jvm
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        } , "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        } , "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        } , "C").start();
    }
}
