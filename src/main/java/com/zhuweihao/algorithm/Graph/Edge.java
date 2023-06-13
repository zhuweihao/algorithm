package com.zhuweihao.algorithm.Graph;

/**
 * @Author zhuweihao
 * @Date 2023/6/13 15:29
 * @Description com.zhuweihao.algorithm.Graph
 */
public class Edge {
    public Node from;
    public Node to;
    public int weight;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
