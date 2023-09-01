package com.zhuweihao.algorithm.test;

/**
 * @Author zhuweihao
 * @Date 2023/8/1 15:42
 * @Description com.zhuweihao.algorithm.test
 */
public class LocalVariableTest {
    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage();
        int num1 = 1;
        int num2 = 2;
        ma.submit(num1);
        ma.submit(num2);
        double avg = ma.getAvg();
    }
}
