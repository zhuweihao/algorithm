package com.zhuweihao.algorithm.class02;

/**
 * T(N)=aT(N/b)+O(N^d)
 * 其中 a >= 1 and b > 1 是常量，其表示的意义是N表示问题的规模，a表示递归的次数也就是生成的子问题数，b表示每次递归是原来的1/b之一个规模，O(N^d)表示分解和合并所要花费的时间复杂度。
 * ①当d<logb^a时，时间复杂度为O(n^(logb^a))
 * ②当d=logb^a时，时间复杂度为O((n^d)*logn)
 * ③当d>logb^a时，时间复杂度为O(n^d)
 * @Author zhuweihao
 * @Date 2023/4/28 16:20
 * @Description com.zhuweihao.algorithm.class02
 */
public class GetMax {

    //求arr中的最大值
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    /*
    时间复杂度：O(n)
     */
    public static int process(int[] arr, int left, int right) {
        if (arr == null || left > right) {
            throw new RuntimeException("输入错误");
        }
        if (left == right) {
            return arr[left];
        }
        int mid = left + ((right - left) >> 1);
        int leftMax = process(arr, left, mid);
        int rightMax = process(arr, mid, right);
        return Math.max(leftMax, rightMax);
    }
}
