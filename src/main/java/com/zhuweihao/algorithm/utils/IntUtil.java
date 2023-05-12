package com.zhuweihao.algorithm.utils;

/**
 * @Author zhuweihao
 * @Date 2023/4/24 9:57
 * @Description com.zhuweihao.algorithm.utils
 */
public class IntUtil {
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        /*
        Math.random()等概率返回[0,1)上的一个小数
        Math.random() => [0,1)
        Math.random()*N => [0,N)
        (int)(Math.random()*N) => [0,N-1]
         */
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        //System.arraycopy(arr, 0, res, 0, arr.length);
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        } else if (arr1 == null && arr2 == null) {
            return true;
        } else if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int j : arr) {
            System.out.print(" " + j);
        }
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        /*
          异或运算的性质
          假设N为任意实数
          性质1：0 ^ N = N
          性质2：N ^ N = 0
          性质3：异或运算满足交换律与结合律
          重点：我们可以将异或运算理解为二进制的无进位相加！也就是说，当两个数异或的时候，如果某一位同为1，则该位为0并且不向前进位。
         */
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }
}
