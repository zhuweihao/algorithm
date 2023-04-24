package com.zhuweihao.algorithm.class01;

import com.zhuweihao.algorithm.utils.IntUtil;

import java.util.Arrays;

/**
 * @Author zhuweihao
 * @Date 2023/4/24 9:34
 * @Description com.zhuweihao.algorithm.class01
 */
public class BSNearLeft extends IntUtil {
    /**
     * 在有序数组arr上找到满足arr[index]>=num的index的最小值
     *
     * @param arr
     * @param num
     * @return
     */
    public static int nearestIndex(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        int index = -1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] >= num) {
                right = mid - 1;
                index = mid;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    public static int test(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int testTime=500000;
        int maxSize=10;
        int maxValue=100;
        boolean succeed=true;
        for (int i = 0; i < testTime; i++) {
            int[] arr=generateRandomArray(maxSize,maxValue);
            Arrays.sort(arr);
            int value=(int)((maxValue+1)*Math.random())-(int)(maxValue*Math.random());
            if(test(arr,value)!=nearestIndex(arr,value)){
                printArray(arr);
                System.out.println(value);
                System.out.println(test(arr,value));
                System.out.println(nearestIndex(arr,value));
                succeed =false;
                break;
            }
        }
        System.out.println(succeed?"Nice":"Fucking fucked!");
    }
}
