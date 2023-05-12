package com.zhuweihao.algorithm.class04;

import java.util.ArrayList;
import java.util.Arrays;

import static com.zhuweihao.algorithm.utils.IntUtil.*;

/**
 * @Author zhuweihao
 * @Date 2023/5/12 14:07
 * @Description com.zhuweihao.algorithm.class04
 */
public class HeapSort {
    /*
    O(NlogN)
    额外空间复杂度：O(1)
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //O(N*logN)
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        //O(N)，是上面方法的优化
//        for (int i = arr.length - 1; i >= 0; i--) {
//            heapify(arr, i, arr.length);
//        }
        //O(N*logN)
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }

    private static void heapInsert(int[] heap, int index) {
        int root = (index - 1) / 2;
        //注意：-1/2=0，故此处不需要对数组越界进行判断
        while (heap[index] > heap[root]) {
            swap(heap, index, root);
            index = root;
            root = (index - 1) / 2;
        }
    }

    /*
    logN
     */
    private static void heapify(int[] heap, int root, int size) {
        int left = 2 * root + 1;
        int right = left + 1;
        while (left < size) {
            int maxOfLeftAndRight = right < size && heap[left] < heap[right] ? right : left;
            int max = heap[maxOfLeftAndRight] > heap[root] ? maxOfLeftAndRight : root;
            if (max == root) {
                break;
            }
            swap(heap, root, max);
            root = maxOfLeftAndRight;
            left = 2 * root + 1;
            right = left + 1;
        }
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static void main(String[] args) {

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                printArray(arr1);
                printArray(arr2);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
