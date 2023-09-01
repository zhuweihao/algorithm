package com.zhuweihao.algorithm.并发编程;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhuweihao
 * @Date 2023/9/1 20:51
 * @Description com.zhuweihao.algorithm.并发编程
 */
public class Profiler {
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<>();

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost:" + Profiler.end() + "mills");
    }
}
