package com.zhuweihao.algorithm.class02;

/**
 * @Author zhuweihao
 * @Date 2023/4/24 21:12
 * @Description com.zhuweihao.algorithm.class02
 */
public class ReverseList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }

    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        while (head != null) {
            Node temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        Node head = node1;
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
        Node node = reverseLinkedList(node1);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }

    }
}
