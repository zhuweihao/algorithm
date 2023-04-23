package com.zhuweihao.algorithm.class01;

/**
 * @Author zhuweihao
 * @Date 2023/4/23 17:09
 * @Description com.zhuweihao.algorithm.class01
 */
public class BSExist {
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int left = 0;
        int right = sortedArr.length - 1;
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return sortedArr[left] == num;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{0,1,2,3,4,5,6,7,8,9,10};
        System.out.println(exist(arr,11));
    }
}
