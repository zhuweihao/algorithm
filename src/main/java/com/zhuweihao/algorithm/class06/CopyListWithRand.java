package com.zhuweihao.algorithm.class06;

import java.util.HashMap;

/**
 * leetcode:138
 *
 * @Author zhuweihao
 * @Date 2023/5/19 16:27
 * @Description com.zhuweihao.algorithm.class06
 */
public class CopyListWithRand {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            Node temp = map.get(cur);
            temp.next = map.get(cur.next);
            temp.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node temp = null;
        while (cur != null) {
            Node node = new Node(cur.val);
            temp = cur.next;
            cur.next = node;
            node.next = temp;
            cur = cur.next.next;
        }
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random != null ? cur.random.next : null;
            cur = cur.next.next;
        }
        cur = head;
        Node res = head.next;
        Node next;
        while (cur != null) {
            temp = cur.next.next;
            next = cur.next;
            cur.next = temp;
            next.next = temp != null ? temp.next : null;
            cur = temp;
        }
        return res;

//        Node help1;
//        Node help2;
//        Node res = head.next;
//        temp = head.next;
//        while (cur.next.next != null) {
//            help1 = cur.next.next;
//            help2 = cur.next.next.next;
//            res.next = help2;
//            cur.next = help1;
//            res = help2;
//            cur = help1;
//        }
//        cur.next = null;
//        res.next = null;
//        return temp;
    }

}
