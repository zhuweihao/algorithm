package com.zhuweihao.algorithm.class01;

import java.util.Arrays;

import static com.zhuweihao.algorithm.utils.IntUtil.*;

/**
 * @Author zhuweihao
 * @Date 2023/3/14 20:51
 * @Description com.zhuweihao.algorithm.class01
 */
public class SelectionSort {
    /**
     * 选择排序：
     * 从未排序区间选出一个最小（大）值，将该元素与未排序区间的第一个元素交换位置，以此类推
     * 时间复杂度：O(n^2)，最好最坏平均都为O(n^2)
     * 空间复杂度：O(1)
     * 不稳定
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0~n-2 , 1~n-2 , 2~n-2 , ......
        for (int i = 0; i < arr.length - 1; i++) {
            //最小值所在位置
            int minIndex = i;
            // i~n-1 , i+1~n-1 , i+2~n-1 , ......
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
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
            selectionSort(arr1);
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
        selectionSort(arr);
        printArray(arr);
    }
}
