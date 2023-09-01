package com.zhuweihao.algorithm.class01;

import com.zhuweihao.algorithm.utils.IntUtil;

/**
 * @Author zhuweihao
 * @Date 2023/4/24 19:44
 * @Description com.zhuweihao.algorithm.class01
 */
public class XOR {
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void swap(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }

    /**
     * 返回奇数次的元素
     *
     * @param arr 只有一个元素出现奇数次，其他元素出现偶数次
     * @return
     */
    public static int findOdd(int[] arr) {
        int xor = 0;
        for (int j : arr) {
            xor ^= j;
        }
        return xor;
    }

    /*
    奇数与1异或相当于减1，偶数与1异或相当于加1。
    找到int数据中最右侧的1的位置。
     */
    public static int findRightOne(int a) {
        for (int i = 1; i <= 32; i++) {
            if ((a ^ 1) == (a - 1)) {
                return i;
            } else {
                a = a >> 1;
            }
        }
        return -1;
    }

    public static void findTwoOdd(int[] arr) {
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }
        /*
        给定int类型的数，提取出最右侧的1，即32bit上除了该位置为1其他全部变为0
        xor & (~xor + 1)
         */
        int rightOne = xor & (~xor + 1);
        int isRightOne = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                isRightOne ^= arr[i];
            }
        }
        System.out.println(isRightOne);
        System.out.println(xor ^ isRightOne);
    }

    /*
    输出一个数的二进制形式有多少个1
     */
    public static int bit1counts(int N) {
        int count = 0;
        while (N != 0) {
            int rightOne = N & ((~N) + 1);
            count++;
            N ^= rightOne;
        }
        return count;
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{1, 2, 3, 2, 1};
//        //如果进行异或运算的两个数的内存地址相同，则不能保持原有值，出错
//        swap(arr, 0, 0);
//        IntUtil.printArray(arr);
//        int a = 1;
//        int b = 2;
//        swap(a, b);
//        System.out.println(a);
//        System.out.println(b);
//
//        //找到数组中出现奇数次的元素
//        int[] arr1 = new int[]{1, 2, 3, 2, 1};
//        System.out.println(findOdd(arr1));
//
//        //找到int数据中最右侧的1的位置
//        System.out.println(findRightOne(8));
//
//        //找到数组中两个出现奇数次的元素
//        findTwoOdd(new int[]{1, 2, 3, 3, 4, 4});
        int num=3;
        System.out.println(bit1counts(6));

    }
}
