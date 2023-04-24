package com.zhuweihao.algorithm.class01;

import java.util.Arrays;

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

    public static void swap(int[] arr, int i, int j) {
        /*
          异或运算的性质
          假设N为任意实数
          性质1：0 ^ N = N
          性质2：N ^ N = 0
          性质3：异或运算满足交换律与结合律
          重点：我们可以将异或运算理解为二进制的无进位相加！也就是说，当两个数异或的时候，如果某一位同为1，则该位为0并且不向前进位。
         */
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        //System.arraycopy(arr, 0, res, 0, arr.length);
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        } else if (arr1 == null && arr2 == null) {
            return true;
        } else if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();
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
