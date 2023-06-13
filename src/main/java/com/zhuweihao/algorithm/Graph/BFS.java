package com.zhuweihao.algorithm.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author zhuweihao
 * @Date 2023/6/13 16:04
 * @Description com.zhuweihao.algorithm.Graph
 */
public class BFS {

    public void bfd(Node node) {
        if (node == null) {
            return;
        }
        HashSet<Node> print = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        print.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.println(node.value);
            for (Node next : node.nexts) {
                if (!print.contains(next)) {
                    queue.add(next);
                    print.add(next);
                }
            }
        }
    }
}
