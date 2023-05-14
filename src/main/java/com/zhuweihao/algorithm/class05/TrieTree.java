package com.zhuweihao.algorithm.class05;

import java.util.HashMap;

/**
 * @Author zhuweihao
 * @Date 2023/5/13 14:26
 * @Description com.zhuweihao.algorithm.class05
 */
public class TrieTree {
    public class Trie1 {
        public class Node {
            private int pass;
            private int end;
            private Node[] nexts;

            public Node() {
                pass = 0;
                end = 0;
                nexts = new Node[26];
            }
        }

        private Node root;

        public Trie1() {
            root = new Node();
        }

        public void insert(String str) {
            if (str == null || str.length() == 0) {
                return;
            }
            char[] charArray = str.toCharArray();
            Node node = root;
            node.pass++;
            for (int i = 0; i < charArray.length; i++) {
                int index = charArray[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        public void erase(String str) {
            if (countStringEqualTo(str) == 0) {
                return;
            }
            char[] charArray = str.toCharArray();
            Node node = root;
            node.pass--;
            for (int i = 0; i < charArray.length; i++) {
                int index = charArray[i] - 'a';
                //如果节点的pass为0，则说明没有字符串使用到该节点，可以直接把后面的节点直接废弃，等待jvm回收
                if (--node.nexts[index].pass == 0) {
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }
            node.end--;
        }

        public int countStringEqualTo(String str) {
            if (str == null || str.length() == 0) {
                return 0;
            }
            char[] charArray = str.toCharArray();
            Node node = root;
            for (int i = 0; i < charArray.length; i++) {
                int index = charArray[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        public int countStringStartWith(String pre) {
            if (pre == null || pre.length() == 0) {
                return 0;
            }
            char[] charArray = pre.toCharArray();
            Node node = root;
            for (int i = 0; i < charArray.length; i++) {
                int index = charArray[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }

    public class Trie2 {
        public class Node {
            private int pass;
            private int end;
            private HashMap<Integer, Node> nexts;

            public Node() {
                pass = 0;
                end = 0;
                nexts = new HashMap<>();
            }
        }

        private Node root;

        public Trie2() {
            root = new Node();
        }

        public void insert(String str) {
            if (str == null || str.length() == 0) {
                return;
            }
            char[] charArray = str.toCharArray();
            Node node = root;
            node.pass++;
            for (int i = 0; i < str.length(); i++) {
                int index = (int) charArray[i];
                if (!node.nexts.containsKey(index)) {
                    node.nexts.put(index, new Node());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }

        public void erase(String str) {
            if (countStringEqualTo(str) <= 0) {
                return;
            }
            char[] charArray = str.toCharArray();
            Node node = root;
            node.pass--;
            for (int i = 0; i < charArray.length; i++) {
                int index = (int) charArray[i];
                if (--node.nexts.get(index).pass == 0) {
                    node.nexts.remove(index);
                    return;
                }
                node = node.nexts.get(index);
            }
            node.end--;
        }

        public int countStringEqualTo(String str) {
            if (str == null || str.length() == 0) {
                return 0;
            }
            char[] charArray = str.toCharArray();
            Node node = root;
            for (int i = 0; i < charArray.length; i++) {
                int index = (int) charArray[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        public int countStringStartWith(String str) {
            if (str == null || str.length() == 0) {
                return 0;
            }
            char[] charArray = str.toCharArray();
            Node node = root;
            for (int i = 0; i < charArray.length; i++) {
                int index = (int) charArray[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }

    }
}
