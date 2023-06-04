package com.zhuweihao.algorithm.Tree.dp;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author zhuweihao
 * @Date 2023/6/1 10:36
 * @Description com.zhuweihao.algorithm.class08
 */
public class IsCBT {
    static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node left = null;
        Node right = null;
        boolean flag = false;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left == null && node.right != null) {
                return false;
            }
            if (flag && (node.left != null || node.right != null)) {
                return false;
            }
            if (!flag && node.right == null) {
                flag = true;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return true;
    }

    static class Message {
        public boolean isCBT;
        public boolean isFull;
        public int height;

        public Message(boolean isCBT, boolean isFull, int height) {
            this.isCBT = isCBT;
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    public static Message process(Node head) {
        if (head == null) {
            return new Message(true, true, 0);
        }
        Message leftMessage = process(head.left);
        Message rightMessage = process(head.right);
        boolean isFull = leftMessage.isFull && rightMessage.isFull && leftMessage.height == rightMessage.height;
        boolean isCBT = false;
        int height = Math.max(leftMessage.height, rightMessage.height) + 1;
        if (isFull) {
            isCBT = true;
        } else {
            if (leftMessage.isCBT && rightMessage.isFull && leftMessage.height - rightMessage.height == 1) {
                isCBT = true;
            }
            if (leftMessage.isFull && rightMessage.isFull && leftMessage.height - rightMessage.height == 1) {
                isCBT = true;
            }
            if (leftMessage.isFull && rightMessage.isCBT && leftMessage.height == rightMessage.height) {
                isCBT = true;
            }
        }
        return new Message(isCBT, isFull, height);
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
