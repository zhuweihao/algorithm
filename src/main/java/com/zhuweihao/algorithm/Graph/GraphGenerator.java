package com.zhuweihao.algorithm.Graph;

/**
 * @Author zhuweihao
 * @Date 2023/6/13 15:41
 * @Description com.zhuweihao.algorithm.Graph
 */
public class GraphGenerator {
    public static Graph getGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int[] ints : matrix) {
            int weight = ints[0];
            int from = ints[1];
            int to = ints[2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(fromNode, toNode, weight);
            graph.edges.add(edge);
            fromNode.out++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
            toNode.in++;
        }
        return graph;
    }
}
