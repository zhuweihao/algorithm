package com.zhuweihao.algorithm.class08;

/**
 * @Author zhuweihao
 * @Date 2023/5/26 11:27
 * @Description com.zhuweihao.algorithm.class08
 */
public class PaperFolding {

    public static void printAllFolds(int N) {
        process(1, N, true);
        System.out.println();
    }

    public static void process(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        process(i + 1, N, true);
        System.out.println(down ? "down" : "up");
        process(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 3;
        printAllFolds(N);
    }
}
