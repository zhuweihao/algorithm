package com.zhuweihao.algorithm.class06;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhuweihao
 * @Date 2023/5/19 11:33
 * @Description com.zhuweihao.algorithm.class06
 */
public class SmallEqualBig {
    static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node partitionList1(Node head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }
        Node cur = head;
        int size = 0;
        while (cur != null) {
            cur = cur.next;
            size++;
        }
        Node[] nodes = new Node[size];
        cur = head;
        for (int i = 0; i < size; i++) {
            nodes[i] = cur;
            cur = cur.next;
        }
        nodePartition(nodes, pivot);
        for (int i = 0; i < size - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        nodes[size - 1].next = null;
        return nodes[0];
    }

    private static void nodePartition(Node[] nodes, int pivot) {
        int left = -1;
        int right = nodes.length;
        int index = 0;
        while (index < right) {
            if (nodes[index].value < pivot) {
                swap(nodes, left + 1, index);
                left++;
                index++;
            } else if (nodes[index].value == pivot) {
                index++;
            } else {
                right--;
                swap(nodes, index, right);
            }
        }
    }

    private static void swap(Node[] nodes, int i, int j) {
        Node temp = nodes[j];
        nodes[j] = nodes[i];
        nodes[i] = temp;
    }

    public static Node partitionList2(Node head, int pivot) {
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node bigHead = null;
        Node bigTail = null;
        while (head != null) {
            Node next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = smallTail.next;
                }
            } else if (head.value == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = equalTail.next;
                }
            } else {
                if (bigHead == null) {
                    bigHead = head;
                    bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = bigTail.next;
                }
            }
            head = next;
        }
        if (smallTail != null) {
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;
        }
        if (equalTail != null) {
            equalTail.next = bigHead;
        }
        return smallHead == null ? (equalHead == null ? bigHead : equalHead) : smallHead;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//        head1 = partitionList1(head1, 4);
        head1 = partitionList2(head1, 5);
        printLinkedList(head1);

    }



}
