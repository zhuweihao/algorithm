package com.zhuweihao.algorithm.class01;

/**
 * @Author zhuweihao
 * @Date 2023/4/24 10:17
 * @Description com.zhuweihao.algorithm.class01
 */
public class BSLocalMin {
    /**
     * 返回任意一个随机最小值
     *
     * @param arr 无序数组，且相邻元素不相等
     * @return
     */
    public static int localMinimum(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid=left+((right-left)>>1);
            if(arr[mid]>arr[mid-1]){
                right=mid-1;
            }else if (arr[mid]>arr[mid+1]){
                left=mid+1;
            }else {
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{3,1,2,5,3,4,6};
        System.out.println(localMinimum(arr));
    }
}
