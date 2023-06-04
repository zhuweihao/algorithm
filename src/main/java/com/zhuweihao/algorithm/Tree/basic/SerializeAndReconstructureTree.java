package com.zhuweihao.algorithm.Tree.basic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
 * 以下代码全部实现了。
 * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
 * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
 * 比如如下两棵树
 * __2
 * /
 * 1
 * 和
 * 1__
 * \
 * 2
 * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
 *
 * @Author zhuweihao
 * @Date 2023/5/21 17:11
 * @Description com.zhuweihao.algorithm.class07
 */
public class SerializeAndReconstructureTree {
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

    public static Queue<String> preSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        pres(head, queue);
        return queue;
    }

    private static void pres(Node head, Queue<String> queue) {
        if (head == null) {
            queue.add(null);
            return;
        }
        queue.add(String.valueOf(head.value));
        pres(head.left, queue);
        pres(head.right, queue);
    }

    public static Node buildByPreQueue(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        return preb(queue);
    }

    private static Node preb(Queue<String> queue) {
        String value = queue.poll();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = preb(queue);
        head.right = preb(queue);
        return head;
    }

    public static Queue<String> posSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        poss(head, queue);
        return queue;
    }

    private static void poss(Node head, Queue<String> queue) {
        if (head == null) {
            queue.add(null);
            return;
        }
        poss(head.left, queue);
        poss(head.right, queue);
        queue.add(String.valueOf(head.value));
    }

    public static Node buildByPosQueue(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        // 左右中  ->  stack(中右左)
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return posb(stack);
    }

    private static Node posb(Stack<String> stack) {
        String value = stack.pop();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.right = posb(stack);
        head.left = posb(stack);
        return head;
    }

    public static Queue<String> levelSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            ans.add(String.valueOf(head.value));
            while (!queue.isEmpty()) {
                head = queue.poll();
                if (head.left != null) {
                    queue.add(head.left);
                    ans.add(String.valueOf(head.left.value));
                } else {
                    ans.add(null);
                }
                if (head.right != null) {
                    queue.add(head.right);
                    ans.add(String.valueOf(head.right.value));
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    public static Node buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        Node head = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }

    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.valueOf(val));
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

    // for test
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            Queue<String> level = levelSerial(head);
            Node preBuild = buildByPreQueue(pre);
            Node posBuild = buildByPosQueue(pos);
            Node levelBuild = buildByLevelQueue(level);
            if (!isSameValueStructure(preBuild, posBuild)) {
                System.out.println("Oops1!");
            }
            if (!isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops2!");
            }
        }
        System.out.println("test finish!");

    }
}
