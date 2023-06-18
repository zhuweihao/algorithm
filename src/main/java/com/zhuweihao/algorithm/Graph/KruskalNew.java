package com.zhuweihao.algorithm.Graph;

import java.io.*;
import java.util.Arrays;

/**
 * https://www.nowcoder.com/questionTerminal/c23eab7bb39748b6b224a8a3afbe396b?
 *
 * @Author zhuweihao
 * @Date 2023/6/14 15:02
 * @Description com.zhuweihao.algorithm.Graph
 */
public class KruskalNew {
    public static int MAXN = 10001;
    public static int MAXM = 100001;
    public static int[] parent = new int[MAXN];
    public static int[] size = new int[MAXN];
    public static int[] help = new int[MAXN];

    public static int[][] edges = new int[MAXM][3];

    public static void init(int n) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    public static int find(int index) {
        int hi = 0;
        while (index != parent[index]) {
            help[hi++] = index;
            index = parent[index];
        }
        for (hi--; hi >= 0; hi--) {
            parent[help[hi]] = index;
        }
        return index;
    }
    public static boolean isSameSet(int i, int j) {
        return find(i) == find(j);
    }

    public static boolean union(int i, int j) {
        int f1 = find(i);
        int f2 = find(j);
        if (f1 != f2) {
            int s1 = size[f1];
            int s2 = size[f2];
            int big = s1 > s2 ? f1 : f2;
            int small = big == f1 ? f2 : f1;
            parent[small] = big;
            size[big] += size[small];
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                edges[i][0] = (int) in.nval;
                in.nextToken();
                edges[i][1] = (int) in.nval;
                in.nextToken();
                edges[i][2] = (int) in.nval;
            }
            Arrays.sort(edges, 0, m, (a, b) -> a[2] - b[2]);
            init(n);
            int ans = 0;
            for (int[] edge : edges) {
                if (union(edge[0], edge[1])) {
                    ans += edge[2];
                }
            }
            out.println(ans);
            out.flush();
        }
    }
}
