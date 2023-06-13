package com.zhuweihao.algorithm.DisjointSet;

import java.io.*;

/**
 * 测试链接 : https://www.nowcoder.com/questionTerminal/e7ed657974934a30b2010046536a5372
 *
 * @Author zhuweihao
 * @Date 2023/6/12 14:14
 * @Description com.zhuweihao.algorithm.DisjointSet
 */
public class UnionFind {

    public static int MAXN = 1000001;
    public static int[] parent = new int[MAXN];
    public static int[] size = new int[MAXN];
    public static int[] help = new int[MAXN];

    public static void init(int n) {
        for (int i = 0; i < MAXN; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public static int find(int index) {
        int i = 0;
        while (index != parent[index]) {
            help[i++] = index;
            index = parent[index];
        }
        i--;
        while (i >= 0) {
            parent[help[i]] = index;
            i--;
        }
        return index;
    }

    public static boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }

    public static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if (aParent != bParent) {
            int big = size[aParent] > size[bParent] ? aParent : bParent;
            int small = big == aParent ? bParent : aParent;
            parent[small] = big;
            size[big] = size[big] + size[small];
            size[small] = 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out));
        while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) streamTokenizer.nval;
            init(n);
            streamTokenizer.nextToken();
            int m = (int) streamTokenizer.nval;
            for (int i = 0; i < m; i++) {
                streamTokenizer.nextToken();
                int op = (int) streamTokenizer.nval;
                streamTokenizer.nextToken();
                int x = (int) streamTokenizer.nval;
                streamTokenizer.nextToken();
                int y = (int) streamTokenizer.nval;
                if (op == 1) {
                    printWriter.println(isSameSet(x, y) ? "Yes" : "No");
                    printWriter.flush();
                } else {
                    union(x, y);
                }
            }
        }
    }
}
