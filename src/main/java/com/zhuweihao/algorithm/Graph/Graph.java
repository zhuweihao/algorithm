package com.zhuweihao.algorithm.Graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author zhuweihao
 * @Date 2023/6/13 15:33
 * @Description com.zhuweihao.algorithm.Graph
 */
public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
