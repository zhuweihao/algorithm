package com.zhuweihao.algorithm.class02;

/**
 * @Author zhuweihao
 * @Date 2023/4/25 20:35
 * @Description com.zhuweihao.algorithm.class02
 */
public class DeleteGivenValue {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node removeValue(Node head, int value) {
        while (head != null) {
            if (head.value != value) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == value) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
