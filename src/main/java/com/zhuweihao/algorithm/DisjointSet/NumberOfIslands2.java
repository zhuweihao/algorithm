package com.zhuweihao.algorithm.DisjointSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhuweihao
 * @Date 2023/6/13 11:54
 * @Description com.zhuweihao.algorithm.DisjointSet
 */
public class NumberOfIslands2 {
    static class Solution {
        public static List<Integer> numIslands2(int m, int n, int[][] positions) {
            Union union=new Union(m,n);
            List<Integer> res=new ArrayList<>();
            for(int[] position:positions){
                res.add(union.connect(position[0],position[1]));
            }
            return res;
        }
    }
    static class Union{
        public int[] parent;
        public int[] size;
        public int[] help;
        public int row;
        public int col;
        public int sets;
        public Union(int m,int n){
            int len=m*n;
            row=m;
            col=n;
            parent=new int[len];
            size=new int[len];
            help=new int[len];
            sets=0;
        }

        public int index(int i,int j){
            return i*col+j;
        }

        public int find(int i){
            int hi=0;
            while(i!=parent[i]){
                help[hi++]=i;
                i=parent[i];
            }
            for(hi--;hi>=0;hi--){
                parent[help[hi]]=i;
            }
            return i;
        }
        public void union(int a, int b, int i, int j) {
            if (a < 0 || a >= row || b < 0 || b >= col || i < 0 || i >= row || j < 0 || j >= col) {
                return;
            }
            int index1 = index(a, b);
            int index2 = index(i, j);
            if (size[index1] == 0 || size[index2] == 0) {
                return;
            }
            int f1 = find(index1);
            int f2 = find(index2);
            if (f1 != f2) {
                int s1 = size[f1];
                int s2 = size[f2];
                int big = s1 > s2 ? f1 : f2;
                int small = big == f1 ? f2 : f1;
                parent[small] = big;
                size[big] += size[small];
                sets--;
            }
        }

        public int connect(int i, int j) {
            int index = index(i, j);
            if (size[index] != 0) {
                return sets;
            }
            parent[index] = index;
            size[index] = 1;
            sets++;
            union(i, j, i - 1, j);
            union(i, j, i + 1, j);
            union(i, j, i, j - 1);
            union(i, j, i, j + 1);
            return sets;
        }
    }

    public static void main(String[] args) {
        int[][] position = new int[][]{{0, 1}, {0, 0}};
        Solution.numIslands2(1, 2, position);
    }
}
