package com.zhuweihao.algorithm.Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 测试链接：https://www.lintcode.com/problem/127/
 *
 * @Author zhuweihao
 * @Date 2023/6/14 9:28
 * @Description com.zhuweihao.algorithm.Graph
 */
public class TopologyOrderDFS1 {

    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    class Record {
        public DirectedGraphNode node;
        public long nodes;

        public Record(DirectedGraphNode node, long nodes) {
            this.node = node;
            this.nodes = nodes;
        }
    }

    class MyComparator implements Comparator<Record> {

        @Override
        public int compare(Record n1, Record n2) {
            return (n2.nodes - n1.nodes) < 0 ? -1 : (n2.nodes - n1.nodes) == 0 ? 0 : 1;
        }
    }

    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        HashMap<DirectedGraphNode, Record> recordMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            getRecord(node, recordMap);
        }
        List<Record> records = new ArrayList<>();
        for (DirectedGraphNode node : graph) {
            records.add(recordMap.get(node));
        }
        records.sort(new MyComparator());
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        for (Record r : records) {
            res.add(r.node);
        }
        return res;
    }

    public Record getRecord(DirectedGraphNode node, HashMap<DirectedGraphNode, Record> recordMap) {
        if (recordMap.containsKey(node)) {
            return recordMap.get(node);
        }
        long nodes = 1;
        for (DirectedGraphNode neighbor : node.neighbors) {
            nodes += getRecord(neighbor, recordMap).nodes;
        }
        Record res = new Record(node, nodes);
        recordMap.put(node, res);
        return res;
    }
}
