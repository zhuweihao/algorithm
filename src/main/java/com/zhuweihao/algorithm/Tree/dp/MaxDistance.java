package com.zhuweihao.algorithm.Tree.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author zhuweihao
 * @Date 2023/5/26 16:33
 * @Description com.zhuweihao.algorithm.class08
 */
public class MaxDistance {
    static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    static class Message {
        private int distance;
        private int height;

        public Message(int distance, int height) {
            this.distance = distance;
            this.height = height;
        }
    }

    public static int maxDistance(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).distance;
    }

    public static Message process(Node head) {
        if (head == null) {
            return new Message(0, 0);
        }
        Message leftMessage = process(head.left);
        Message rightMessage = process(head.right);
        int height = Math.max(leftMessage.height, rightMessage.height) + 1;
        int distance = leftMessage.height + rightMessage.height + 1;
        distance = Math.max(leftMessage.distance, distance);
        distance = Math.max(rightMessage.distance, distance);
        return new Message(distance, height);
    }

    public static int maxDistance1(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = getPrelist(head);
        HashMap<Node, Node> parentMap = getParentMap(head);
        int max = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i; j < arr.size(); j++) {
                max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
            }
        }
        return max;
    }

    public static ArrayList<Node> getPrelist(Node head) {
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        return arr;
    }

    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static HashMap<Node, Node> getParentMap(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        map.put(head, null);
        fillParentMap(head, map);
        return map;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    public static int distance(HashMap<Node, Node> parentMap, Node o1, Node o2) {
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        Node lowestAncestor = cur;
        cur = o1;
        int distance1 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance1++;
        }
        cur = o2;
        int distance2 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance2++;
        }
        return distance1 + distance2 - 1;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxDistance1(head) != maxDistance(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
