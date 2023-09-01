package com.zhuweihao.algorithm.test;


import com.zhuweihao.algorithm.utils.InAndOut;

import java.util.ArrayList;

/**
 * @Author zhuweihao
 * @Date 2023/8/4 10:43
 * @Description com.zhuweihao.algorithm.test
 */
public class test {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long l = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(l == (a + b));
        System.out.println(l.equals(a + b));
    }
}
