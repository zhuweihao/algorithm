package com.zhuweihao.algorithm.Graph;

import java.util.*;

/**
 * @Author zhuweihao
 * @Date 2023/6/13 17:22
 * @Description com.zhuweihao.algorithm.Graph
 */
public class TopologySort {
    public ArrayList<Node> topologySort(Graph graph) {
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            if (!inMap.containsKey(node)) {
                inMap.put(node, node.in);
                if (node.in == 0) {
                    zeroQueue.add(node);
                }
            }
        }
        ArrayList<Node> res = new ArrayList<>();
        while (!zeroQueue.isEmpty()) {
            Node node = zeroQueue.poll();
            res.add(node);
            for (Node next : node.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroQueue.add(next);
                }
            }
        }
        return res;
    }
}
