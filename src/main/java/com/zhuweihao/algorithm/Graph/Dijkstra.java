package com.zhuweihao.algorithm.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * @Author zhuweihao
 * @Date 2023/6/14 15:58
 * @Description com.zhuweihao.algorithm.Graph
 */
public class Dijkstra {
    public HashMap<Node, Integer> dijkstra(Node start) {
        HashMap<Node, Integer> res = new HashMap<>();
        HashSet<Node> selectedNodes = new HashSet<>();
        res.put(start, 0);
        Node minNode = getMinDistanceAndUnselectedNode(res, selectedNodes);
        while (minNode != null) {
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!res.containsKey(toNode)){
                    res.put(toNode,edge.weight);
                }else {
                    if (res.get(toNode) > res.get(minNode) + edge.weight) {
                        res.put(toNode, res.get(minNode) + edge.weight);
                    }
                }

            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(res, selectedNodes);
        }
        return res;
    }

    public Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distances, HashSet<Node> selectedNodes) {
        Node res = null;
        int minDistance = Integer.MAX_VALUE;
        for (Entry<Node, Integer> entry : distances.entrySet()) {
            if (minDistance > entry.getValue() && !selectedNodes.contains(entry.getKey())) {
                minDistance = entry.getValue();
                res = entry.getKey();
            }
        }
        return res;
    }
}
