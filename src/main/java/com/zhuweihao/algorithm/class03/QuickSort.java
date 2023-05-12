package com.zhuweihao.algorithm.class03;

import static com.zhuweihao.algorithm.utils.IntUtil.*;

/**
 * @Author zhuweihao
 * @Date 2023/5/11 11:45
 * @Description com.zhuweihao.algorithm.class03
 */
public class QuickSort {
    /**
     * 以arr[right]做划分值num，左边的值<= num，右边的值>num
     *
     * @param arr
     * @param left
     * @param right
     * @return 返回数组中小于等于arr[right]部分的右边界
     */
    public static int partition(int[] arr, int left, int right) {
        if (left > right) {
            return -1;
        }
        if (left == right) {
            return left;
        }
        int lessOrEqual = left - 1;
        int index = left;
        while (index < right) {
            if (arr[index] <= arr[right]) {
                swap(arr, index, lessOrEqual + 1);
                index++;
                lessOrEqual++;
//                swap(arr,index++,++lessOrEqual);
            } else {
                index++;
            }
        }
        swap(arr, ++lessOrEqual, right);
        return lessOrEqual;
    }

    /**
     * 荷兰国旗问题
     * 以arr[right]做划分值num，将数组化成三部分，<num    =num    >num
     *
     * @param arr
     * @param left
     * @param right
     * @return 返回等于arr[right]部分的左边界和右边界
     */
    public static int[] netherlandsFlag(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, left};
        }
        int less = left - 1;
        int more = right;
        int index = left;
        while (index < more) {
            if (arr[index] < arr[right]) {
                swap(arr, index++, ++less);
            } else if (arr[index] == arr[right]) {
                index++;
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, right);
        return new int[]{less + 1, more};
    }

    /*
    O(N^2)
     */
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    private static void process1(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int fixedPosition = partition(arr, left, right);
        process1(arr, left, fixedPosition - 1);
        process1(arr, fixedPosition + 1, right);
    }

    /*
    O(N^2)
     */
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    private static void process2(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] equalArea = netherlandsFlag(arr, left, right);
        process2(arr, left, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, right);
    }

    /*
    O(N*logN)
    空间复杂度：O(logN)
     */
    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    private static void process3(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(arr, left + (int) (Math.random() * (right - left + 1)), right);
        int[] equalArea = netherlandsFlag(arr, left, right);
        process3(arr, left, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, right);
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }
}
