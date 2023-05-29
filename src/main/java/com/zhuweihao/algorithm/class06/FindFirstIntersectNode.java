package com.zhuweihao.algorithm.class06;

/**
 * @Author zhuweihao
 * @Date 2023/5/19 18:46
 * @Description com.zhuweihao.algorithm.class06
 */
public class FindFirstIntersectNode {
    static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        } else if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        } else {
            return null;
        }
    }

    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        int length1 = 1;
        while (cur1.next != null) {
            cur1 = cur1.next;
            length1++;
        }
        Node cur2 = head2;
        int length2 = 1;
        while (cur2.next != null) {
            cur2 = cur2.next;
            length2++;
        }
        if (cur1 == cur2) {
            cur1 = length1 >= length2 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            for (int i = 0; i < Math.abs(length1 - length2); i++) {
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            return null;
        }
    }

    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        if (loop1 == loop2) {
            Node cur1 = head1;
            int length1 = 1;
            while (cur1 != loop1) {
                cur1 = cur1.next;
                length1++;
            }
            Node cur2 = head2;
            int length2 = 1;
            while (cur2 != loop1) {
                cur2 = cur2.next;
                length2++;
            }
            cur1 = length1 >= length2 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            for (int i = 0; i < Math.abs(length1 - length2); i++) {
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            Node cur = loop1.next;
            while (cur != loop1) {
                cur = cur.next;
                if (cur == loop2) {
                    break;
                }
            }
            if (cur == loop1) {
                return null;
            } else {
                return loop1;
            }
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
