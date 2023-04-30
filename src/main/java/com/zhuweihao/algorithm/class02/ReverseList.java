package com.zhuweihao.algorithm.class02;

import com.zhuweihao.algorithm.utils.LinkedListUtil.Node;
import com.zhuweihao.algorithm.utils.LinkedListUtil.DoubleNode;
import java.util.List;

import static com.zhuweihao.algorithm.utils.LinkedListUtil.*;

/**
 * @Author zhuweihao
 * @Date 2023/4/24 21:12
 * @Description com.zhuweihao.algorithm.class02
 */
public class ReverseList {

    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
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

    public static boolean checkLinkedListReverse(List<Integer> origin, Node head) {
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean checkDoubleLinkedListReverse(List<Integer> origin, DoubleNode head) {
        DoubleNode end = null;
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            end = head;
            head = head.next;
        }
        for (int i = 0; i < origin.size(); i++) {
            assert end != null;
            if (!origin.get(i).equals(end.value)) {
                return false;
            }
            end = end.last;
        }
        return true;
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testNum = 10000;
        System.out.println("test begin!");
        for (int i = 0; i < testNum; i++) {
            Node node = generateRandomLinkedList(len, value);
            List<Integer> list = getLinkedListOriginOrder(node);
            node = reverseLinkedList(node);
            if (!checkLinkedListReverse(list, node)) {
                System.out.println("LinkedList reverse error!");
            }

            DoubleNode doubleNode = generateRandomDoubleLinkedList(len, value);
            List<Integer> doubleList = getDoubleListOriginOrder(doubleNode);
            doubleNode = reverseDoubleLinkedList(doubleNode);
            if (!checkDoubleLinkedListReverse(doubleList, doubleNode)) {
                System.out.println("doubleLinkedList reverse error");
            }
        }
        System.out.println("test finish!");
    }
}
