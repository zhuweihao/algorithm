package com.zhuweihao.algorithm.class01;

import java.util.Arrays;

import static com.zhuweihao.algorithm.utils.IntUtil.*;

/**
 * @Author zhuweihao
 * @Date 2023/3/14 21:25
 * @Description com.zhuweihao.algorithm.class01
 */
public class BubbleSort {
    /**
     * 冒泡排序：
     * 在未排序区间内，从头开始两两比较，如果后面的值大（小）于前面的值则进行交换
     * 时间复杂度：O(n^2)，最好最坏平均都为O(n^2)
     * 空间复杂度：O(1)
     * 稳定
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 1~n-1 , 1~n-2 , 1~n-3 , ... , 1
        for (int i = arr.length - 1; i > 0; i--) {
            // 0 1 ， 1 2 ， 2 3 ，... , i-1 i
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }



    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static void main(String[] args) {
        int testNum = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testNum; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            bubbleSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked");
        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }
}
