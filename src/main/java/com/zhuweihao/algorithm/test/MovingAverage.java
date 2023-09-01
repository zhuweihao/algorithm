package com.zhuweihao.algorithm.test;

/**
 * @Author zhuweihao
 * @Date 2023/8/1 15:41
 * @Description com.zhuweihao.algorithm.test
 */
public class MovingAverage {
    private int count = 0;
    private double sum = 0.0D;

    public void submit(double value) {
        this.count++;
        this.sum += value;
    }

    public double getAvg() {
        if (0 == this.count) {
            return sum;
        }
        return this.sum / this.count;
    }
}
