package com.zhuweihao.algorithm.class04;

import java.util.Arrays;
import java.util.PriorityQueue;

import static com.zhuweihao.algorithm.utils.IntUtil.*;

/**
 * @Author zhuweihao
 * @Date 2023/5/12 15:47
 * @Description com.zhuweihao.algorithm.class04
 */
public class SortArrayDistanceLessK {
    /*
    时间复杂度O(NlogK)
     */
    public static void sortArrayDistanceLessK(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(k + 1);
        for (int i = 0; i <= k && i < arr.length; i++) {
            heap.add(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.poll();
            if ((i + k + 1) < arr.length) {
                heap.add(arr[i + k + 1]);
            }
        }
    }

    // for test
    public static void comparator(int[] arr, int k) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }

    // for test
    public static void main(String[] args) {
        System.out.println("test begin");
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            sortArrayDistanceLessK(arr1, k);
            comparator(arr2, k);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                printArray(arr);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
