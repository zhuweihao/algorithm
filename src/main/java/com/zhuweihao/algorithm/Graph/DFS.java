package com.zhuweihao.algorithm.Graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * @Author zhuweihao
 * @Date 2023/6/13 16:16
 * @Description com.zhuweihao.algorithm.Graph
 */
public class DFS {
    public HashSet<Node> print = new HashSet<>();

    public void dfs(Node node) {
        if (node == null) {
            return;
        }
        print.add(node);
        System.out.println(node.value);
        for (Node next : node.nexts) {
            if (!print.contains(next)){
                dfs(next);
            }
        }
    }

    public void dfs1(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> print = new HashSet<>();
        stack.add(node);
        print.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            node = stack.pop();
            for (Node next : node.nexts) {
                if (!print.contains(next)) {
                    stack.push(node);
                    stack.push(next);
                    print.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
