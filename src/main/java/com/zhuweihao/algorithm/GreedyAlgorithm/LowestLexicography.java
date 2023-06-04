package com.zhuweihao.algorithm.GreedyAlgorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 给定一个由字符串组成的数组strs，必须把所有的字符串拼接起来，返回所有可能的拼接结果中字典序最小的结果
 *
 * @Author zhuweihao
 * @Date 2023/6/2 16:09
 * @Description com.zhuweihao.algorithm.GreedyAlgorithm
 */
public class LowestLexicography {

    public static String lowestLexicograpgy1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        return process(strs).first();
    }

    public static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<>();
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nexts = removeFirst(strs, i);
            TreeSet<String> next = process(nexts);
            if (next.isEmpty()) {
                ans.add(first);
            } else {
                for (String str : next) {
                    ans.add(first + str);
                }
            }
        }
        return ans;
    }

    private static String[] removeFirst(String[] strs, int index) {
        String[] ans = new String[strs.length - 1];
        for (int i = 0; i < strs.length - 1; i++) {
            if (i < index) {
                ans[i] = strs[i];
            } else {
                ans[i] = strs[i + 1];
            }
        }
        return ans;
    }

    /*
    贪心策略：(a.b)<(b.a)
     */
    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String lowestLexicography2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        Arrays.sort(strs, new MyComparator());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            stringBuilder.append(strs[i]);
        }
        return stringBuilder.toString();
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestLexicograpgy1(arr1).equals(lowestLexicography2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
