package com.zhuweihao.algorithm.class01;

/**
 * @Author zhuweihao
 * @Date 2023/4/23 17:09
 * @Description com.zhuweihao.algorithm.class01
 */
public class BSExist {
    /**
     * 二分法判定有序数组sortedArr中是否存在num
     * 时间复杂度：O(logN)
     *
     * @param sortedArr
     * @param num
     * @return
     */
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int left = 0;
        int right = sortedArr.length - 1;
        int mid = 0;
        while (left < right) {
            /*
            mid = (left + right) / 2
            mid = left + (right -left) / 2
            mid = left + ((right - left)>>1)
            除以二就相当于N>>1，乘以2相当于N<<1，乘以2加1相当于  (N<<1)|1
             */
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
        int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(exist(arr, 10));
    }
}
