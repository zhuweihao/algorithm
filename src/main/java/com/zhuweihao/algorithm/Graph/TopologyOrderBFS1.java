package com.zhuweihao.algorithm.Graph;

import java.util.*;

/**
 * @Author zhuweihao
 * @Date 2023/6/14 10:40
 * @Description com.zhuweihao.algorithm.Graph
 */
public class TopologyOrderBFS1 {
    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        HashMap<DirectedGraphNode, Integer> indegree = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            indegree.put(node, 0);
        }
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegree.put(neighbor, indegree.get(neighbor) + 1);
            }
        }
        Queue<DirectedGraphNode> zeroQueue = new LinkedList<>();
        for (DirectedGraphNode node : graph) {
            if (indegree.get(node) == 0) {
                zeroQueue.add(node);
            }
        }
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        while (!zeroQueue.isEmpty()) {
            DirectedGraphNode node = zeroQueue.poll();
            res.add(node);
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    zeroQueue.add(neighbor);
                }
            }
        }
        return res;
    }
}
