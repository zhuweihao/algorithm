package com.zhuweihao.algorithm.class03;

import static com.zhuweihao.algorithm.utils.IntUtil.*;

/**
 * 归并排序
 * 归并排序没有浪费比较行为，插入、选择、冒泡浪费了很多比较行为
 * 归并排序的实质是把比较行为变成了有序信息并传递，比O(N^2)的排序快
 *
 * @Author zhuweihao
 * @Date 2023/4/30 14:19
 * @Description com.zhuweihao.algorithm.class03
 */
public class MergeSort {

    /*
    递归实现
     */
    public static void MergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    /*
    T(N) = 2*T(N/2) + O(N)
    时间复杂度：O(N*logN)
     */
    public static void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
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
    }

    /*
    非递归实现
     */
    public static void MergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int length = arr.length;
        int mergeSize = 1;
        while (mergeSize < length) {
            int left = 0;
            while (left < length) {
                int mid = left + mergeSize - 1;
                if (mid >= length) {
                    break;
                }
                int right = Math.min(mid + mergeSize, length - 1);
                merge(arr, left, mid, right);
                left = right + 1;
            }
            if (mergeSize > length / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }


    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            MergeSort1(arr1);
            MergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");

    }
}
