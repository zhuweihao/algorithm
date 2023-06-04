package com.zhuweihao.algorithm.Tree.dp;

/**
 * @Author zhuweihao
 * @Date 2023/5/26 16:13
 * @Description com.zhuweihao.algorithm.class08
 */
public class IsBalanced {
    static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    static class Message {
        private int height;
        private boolean isBalanced;

        public Message(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public static Boolean isBalanced(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBalanced;
    }

    public static Message process(Node head) {
        if (head == null) {
            return new Message(0, true);
        }
        Message messageLeft = process(head.left);
        Message messageRight = process(head.right);
        int height = Math.max(messageLeft.height, messageRight.height) + 1;
        Message message = new Message(height, true);
        if (!messageLeft.isBalanced || !messageRight.isBalanced || Math.abs(messageLeft.height - messageRight.height) > 1) {
            message.isBalanced = false;
        }
        return message;
    }

    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
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
            if (isBalanced1(head) != isBalanced(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
