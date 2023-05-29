package com.zhuweihao.algorithm.class08;


/**
 * @Author zhuweihao
 * @Date 2023/5/25 10:32
 * @Description com.zhuweihao.algorithm.class08
 */
public class PrintBinaryTree {

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

    public static void printBinaryTree(Node head) {
        if (head == null) {
            return;
        }
        System.out.println("Binary Tree:");
        printTreeInOrder(head, 0, "H", 20);
        System.out.println();
    }

    private static void printTreeInOrder(Node head, int height, String position, int len) {
        if (head == null) {
            return;
        }
        printTreeInOrder(head.right, height + 1, "v", len);

        String value = position + head.value + position;
        int lengthMid = value.length();
        int lengthLeft = (len - lengthMid) / 2;
        int lengthRight = len - lengthMid - lengthLeft;
        value = getSpace(lengthLeft) + value + getSpace(lengthRight);
        System.out.println(getSpace(height * len) + value);

        printTreeInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buffer = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buffer.append(space);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printBinaryTree(head);

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);
        printBinaryTree(head);

        head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        head.left.left.right = new Node(1);
        printBinaryTree(head);

    }



}
