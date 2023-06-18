package com.zhuweihao.algorithm.Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Author zhuweihao
 * @Date 2023/6/14 11:28
 * @Description com.zhuweihao.algorithm.Graph
 */
public class Prim {

    class MyComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.weight - e2.weight;
        }
    }

    public Set<Edge> primMST(Graph graph) {
        Set<Edge> res = new HashSet<>();
        HashSet<Node> visit = new HashSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>(new MyComparator());
        for (Node node : graph.nodes.values()) {
            if (!visit.contains(node)) {
                visit.add(node);
                for (Edge edge : node.edges) {
                    queue.offer(edge);
                }
                while (!queue.isEmpty()) {
                    Edge edge = queue.poll();
                    if (!visit.contains(edge.to)) {
                        visit.add(edge.to);
                        res.add(edge);
                        for (Edge e : edge.to.edges) {
                            queue.offer(e);
                        }
                    }
                }
            }
            //break;
        }
        return res;
    }

    public static int prim(int[][] graph) {
        int size = graph.length;
        int[] distances = new int[size];
        boolean[] visit = new boolean[size];
        visit[0] = true;
        for (int i = 0; i < size; i++) {
            distances[i] = graph[0][i];
        }
        int sum = 0;
        for (int i = 1; i < size; i++) {
            int minPath = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] < minPath) {
                    minPath = distances[j];
                    minIndex = j;
                }
            }
            if (minIndex == -1) {
                return sum;
            }
            visit[minIndex] = true;
            sum += minPath;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] > graph[minIndex][j]) {
                    distances[j] = graph[minIndex][j];
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{{Integer.MAX_VALUE, 1, 2}, {1, Integer.MAX_VALUE, 3}, {2, 3, Integer.MAX_VALUE}};
        int prim = prim(graph);
        System.out.println(prim);
    }
}
