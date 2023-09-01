package com.zhuweihao.algorithm.test;

/**
 * @Author zhuweihao
 * @Date 2023/8/1 16:01
 * @Description com.zhuweihao.algorithm.test
 */
public class ForLoopTest {
    private static int[] numbers = {1, 6, 8};
    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage();
        for (int number : numbers) {
            ma.submit(number);
        }
        double avg = ma.getAvg();
    }
}
