package com.zhuweihao.algorithm.DisjointSet;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author zhuweihao
 * @Date 2023/6/12 15:16
 * @Description com.zhuweihao.algorithm.DisjointSet
 */
public class UnionFind1 {
    public static class UnionFind<V> {
        public HashMap<V, V> parent;
        public HashMap<V, Integer> size;

        public UnionFind(List<V> values) {
            parent = new HashMap<>();
            size = new HashMap<>();
            for (V value : values) {
                parent.put(value, value);
                size.put(value, 1);
            }
        }

        public V find(V value) {
            Stack<V> path = new Stack<>();
            while (value != parent.get(value)) {
                path.push(value);
                value = parent.get(value);
            }
            while (!path.isEmpty()) {
                parent.put(path.pop(), value);
            }
            return value;
        }

        public boolean isSameSet(V value1, V value2) {
            return find(value1) == find(value2);
        }

        public void union(V a, V b) {
            V aParent = find(a);
            V bParent = find(b);
            if (aParent != bParent) {
                Integer aSize = size.get(aParent);
                Integer bSize = size.get(bParent);
                V big = aSize > bSize ? aParent : bParent;
                V small = big == aParent ? bParent : aParent;
                parent.put(small, big);
                size.put(big, aSize + bSize);
                size.remove(small);
            }
        }
    }
}
