package com.zhuweihao.algorithm.class08;

/**
 * @Author zhuweihao
 * @Date 2023/5/26 10:39
 * @Description com.zhuweihao.algorithm.class08
 */
public class SuccessorNode {
    static class Node {
        private int value;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    public static Node getSuccessorNode(Node head) {
        if (head == null) {
            return head;
        }
        if (head.right != null) {
            return getMostLeft(head.right);
        } else {
            while (head.parent != null) {
                if (head.parent.left == head) {
                    return head.parent;
                }
                if (head.parent.right == head) {
                    head = head.parent;
                }
            }
            return head.parent;
        }
    }

    private static Node getMostLeft(Node head) {
        while (head.left != null) {
            head = head.left;
        }
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }
}
