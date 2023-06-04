package com.zhuweihao.algorithm.GreedyAlgorithm;

import java.util.PriorityQueue;

/**
 * 一块金条切成两半，是需要花费和长度数值一样的铜板
 * 比如长度为20的金条，不管怎么切都要花费20个铜板，一群人想整分整块金条，怎么分最省铜板?
 * 例如，给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 * 如果先把长度60的金条分成10和50，花费60；再把长度50的金条分成20和30，花费50；一共花费110铜板
 * 但如果先把长度60的金条分成30和30，花费60；再把长度30金条分成10和20，花费30；一共花费90铜板
 * 输入一个数组，返回分割的最小代价
 *
 * @Author zhuweihao
 * @Date 2023/6/2 19:23
 * @Description com.zhuweihao.algorithm.GreedyAlgorithm
 */
public class LeastMoneySplitGold {

    public static int leastMoney1(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        return process(arr, 0);
    }

    public static int process(int[] arr, int cost) {
        if (arr.length == 1) {
            return cost;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int[] mergeArr = getMergeArr(arr, i, j);
                int cur = process(mergeArr, cost + arr[j] + arr[i]);
                min = Math.min(min, cur);
            }
        }
        return min;
    }

    private static int[] getMergeArr(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        for (int k = 0, m = 0; k < arr.length; k++) {
            if (k != i && k != j) {
                ans[m++] = arr[k];
            }
        }
        ans[arr.length - 2] = arr[i] + arr[j];
        return ans;
    }

    public static int leastMoney2(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i : arr) {
            priorityQueue.add(i);
        }
        int sum = 0;
        int temp = 0;
        while (priorityQueue.size() > 1) {
            Integer money1 = priorityQueue.poll();
            Integer money2 = priorityQueue.poll();
            temp = money1 + money2;
            priorityQueue.add(temp);
            sum += temp;
        }
        return sum;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (leastMoney1(arr) != leastMoney2(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
