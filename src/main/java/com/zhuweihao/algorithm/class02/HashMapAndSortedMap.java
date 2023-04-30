package com.zhuweihao.algorithm.class02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * @Author zhuweihao
 * @Date 2023/4/30 13:22
 * @Description com.zhuweihao.algorithm.class02
 */
public class HashMapAndSortedMap {
    HashMap<Integer, Integer> map = new HashMap<>();

    HashSet<String> set = new HashSet<>();

    //哈希表，增，删，改，查，在使用时，时间复杂度为O(1)

    //哈希表基础类型的key按值传递，非基础类型的key按引用传递


    //TreeMap就是有序表，使用api时，时间复杂度认为O(logN)
    TreeMap<Integer, String> treeMap = new TreeMap<>();
}
