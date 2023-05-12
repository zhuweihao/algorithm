package com.zhuweihao.algorithm.class03;

import static com.zhuweihao.algorithm.utils.IntUtil.*;

/**
 * @Author zhuweihao
 * @Date 2023/5/9 20:44
 * @Description com.zhuweihao.algorithm.class03
 */
public class SmallSum {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid) + process(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right-left+1];
        int sum = 0;
        int p1 = left;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= right) {
//            sum += arr[p1] < arr[p2] ? (arr[p1] * (right - p2 + 1)) : 0;
//            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
            if (arr[p1] < arr[p2]) {
                help[i++] = arr[p1];
                sum += arr[p1] * (right - p2 + 1);
                p1++;
            } else {
                help[i++] = arr[p2++];
            }
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];
        }
        return sum;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
