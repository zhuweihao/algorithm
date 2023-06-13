package com.zhuweihao.algorithm.Graph;

import java.util.ArrayList;

/**
 * @Author zhuweihao
 * @Date 2023/6/13 15:24
 * @Description com.zhuweihao.algorithm.Graph
 */
public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
    }
}
