package com.zhuweihao.algorithm.Graph;

import java.util.*;

/**
 * @Author zhuweihao
 * @Date 2023/6/14 11:17
 * @Description com.zhuweihao.algorithm.Graph
 */
public class Kruskal {
    class UnionFind {
        HashMap<Node, Node> parent;
        HashMap<Node, Integer> size;

        public UnionFind() {
            parent = new HashMap<>();
            size = new HashMap<>();
        }

        public void init(Collection<Node> nodes) {
            for (Node node : nodes) {
                parent.put(node, node);
                size.put(node, 1);
            }
        }

        public Node find(Node node) {
            Queue<Node> queue = new LinkedList<>();
            while (parent.get(node) != node) {
                queue.add(node);
                node = parent.get(node);
            }
            while (!queue.isEmpty()) {
                parent.put(queue.poll(), node);
            }
            return node;
        }

        public boolean isSameSet(Node n1, Node n2) {
            return find(n1) == find(n2);
        }

        public void union(Node n1, Node n2) {
            Node f1 = find(n1);
            Node f2 = find(n2);
            if (f1 != f2) {
                Integer s1 = size.get(f1);
                Integer s2 = size.get(f2);
                Node big = s1 > s2 ? f1 : f2;
                Node small = big == f1 ? f2 : f1;
                parent.put(small, big);
                size.put(big, s1 + s2);
            }
        }
    }

    class MyCompatator implements Comparator<Edge> {

        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.weight - e2.weight;
        }
    }

    public Set<Edge> kruskalMST(Graph graph) {
        Set<Edge> res = new HashSet<>();
        UnionFind union = new UnionFind();
        union.init(graph.nodes.values());
        PriorityQueue<Edge> edges = new PriorityQueue<>(new MyCompatator());
        for (Edge edge : graph.edges) {
            edges.add(edge);
        }
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (!union.isSameSet(edge.from, edge.to)) {
                union.union(edge.from, edge.to);
                res.add(edge);
            }
        }
        return res;
    }
}
