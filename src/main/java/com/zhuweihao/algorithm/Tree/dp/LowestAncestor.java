package com.zhuweihao.algorithm.Tree.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author zhuweihao
 * @Date 2023/6/2 14:41
 * @Description com.zhuweihao.algorithm.class08
 */
public class LowestAncestor {
    static class Node {
        public int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<Node> parent = new HashSet<>();
        Node cur = o1;
        parent.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            parent.add(cur);
        }
        cur = o2;
        while (!parent.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    private static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    static class Message {
        public Node ans;
        public boolean find1;
        public boolean find2;

        public Message(Node ans, boolean find1, boolean find2) {
            this.ans = ans;
            this.find1 = find1;
            this.find2 = find2;
        }
    }

    public static Node lowestAncestor2(Node head, Node o1, Node o2) {
        return process(head, o1, o2).ans;
    }

    private static Message process(Node head, Node o1, Node o2) {
        if (head == null) {
            return new Message(null, false, false);
        }
        Message leftMessage = process(head.left, o1, o2);
        Message rightMessage = process(head.right, o1, o2);
        boolean find1 = head == o1 || leftMessage.find1 || rightMessage.find1;
        boolean find2 = head == o2 || leftMessage.find2 || rightMessage.find2;
        Node ans = null;
        if (leftMessage.ans != null) {
            ans = leftMessage.ans;
        }else if (rightMessage.ans != null) {
            ans = rightMessage.ans;
        }else if (find1 && find2) {
            ans = head;
        }
        return new Message(ans, find1, find2);
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

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
