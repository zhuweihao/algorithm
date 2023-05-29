package com.zhuweihao.algorithm.class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author zhuweihao
 * @Date 2023/5/21 16:16
 * @Description com.zhuweihao.algorithm.class07
 */
public class TreeMaxWidth {
    static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static int maxWidthWithMap(Node head) {
        if (head == null) {
            return -1;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node, Integer> map = new HashMap<>();
        map.put(head, 1);
        int max = 0;
        int curLevel = 1;
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            head = queue.poll();
            Integer curNodeLevel = map.get(head);
            if (head.left != null) {
                queue.add(head.left);
                map.put(head.left, curNodeLevel + 1);
            }
            if (head.right != null) {
                queue.add(head.right);
                map.put(head.right, curNodeLevel + 1);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        return Math.max(max, curLevelNodes);
    }

    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return -1;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head;
        Node nextEnd = null;
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            head = queue.poll();
            curLevelNodes++;
            if (head.left != null) {
                queue.add(head.left);
                nextEnd = head.left;
            }
            if (head.right != null) {
                queue.add(head.right);
                nextEnd = head.right;
            }
            if (head == curEnd) {
                max = Math.max(curLevelNodes, max);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

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
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthWithMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }
}
