package com.zhuweihao.algorithm.class01;

import java.util.Arrays;

import static com.zhuweihao.algorithm.utils.IntUtil.*;

/**
 * @Author zhuweihao
 * @Date 2023/4/22 15:39
 * @Description com.zhuweihao.algorithm.class01
 */
public class InsertSort {
    /**
     * 插入排序：
     * n个待排序的元素看成为一个有序表和一个无序表，取无序表的第一个元素与有序表中的元素从后往前进行比较，选择合适位置插入数据
     * 时间复杂度：O(n^2)，最好O(n)
     * 空间复杂度：O(1)
     * 稳定性：稳定
     *
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0~1 , 0~2 , 0~3 , ... , 0~n-1
        for (int i = 1; i < arr.length; i++) {
            //错误示范：这种方式增加了很多次数据交换过程
            //上面说错了，下面注释掉的代码效果是一样的
//            for (int j = i; j > 0; j--) {
//                if (arr[j] < arr[j - 1]) {
//                    swap(arr, j, j - 1);
//                }
//            }
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
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
            insertionSort(arr1);
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
        insertionSort(arr);
        printArray(arr);
    }
}
