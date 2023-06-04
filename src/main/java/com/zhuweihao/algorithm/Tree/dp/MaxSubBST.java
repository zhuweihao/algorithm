package com.zhuweihao.algorithm.Tree.dp;

/**
 * @Author zhuweihao
 * @Date 2023/5/31 14:49
 * @Description com.zhuweihao.algorithm.class08
 */
public class MaxSubBST {

    static class Node {
        private int value;
        private Node left;
        private Node right;
    }

    static class SizeMessage {
        private int max;
        private int min;
        private int size;
        private boolean isBST;

        public SizeMessage(int max, int min, int size, boolean isBST) {
            this.max = max;
            this.min = min;
            this.size = size;
            this.isBST = isBST;
        }
    }

    // 在线测试链接 : https://leetcode.com/problems/largest-bst-subtree
    public static int getMaxSubBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        return process1(head).size;
    }

    public static SizeMessage process1(Node head) {
        if (head == null) {
            return new SizeMessage(0, 0, 0, true);
        }
        SizeMessage leftMessage = process1(head.left);
        SizeMessage rightMessage = process1(head.right);
        if (leftMessage.isBST && rightMessage.isBST) {
            if(leftMessage.size==0&&rightMessage.size==0){
                return new SizeMessage(head.value,head.value,1,true);
            }
            if(leftMessage.size==0&&head.value<rightMessage.min){
                return new SizeMessage(rightMessage.max, head.value, rightMessage.size + 1, true);
            }
            if(rightMessage.size==0&&leftMessage.max<head.value){
                return new SizeMessage(head.value,leftMessage.min,leftMessage.size+1,true);
            }
            if(head.value>leftMessage.max&&head.value<rightMessage.min){
                return new SizeMessage(rightMessage.max, leftMessage.min, leftMessage.size + rightMessage.size + 1, true);
            }
        }
        int max = Math.max(head.value, leftMessage.max);
        max = Math.max(max, rightMessage.max);
        int min = Math.min(head.value, leftMessage.min);
        min = Math.min(min, rightMessage.min);
        int size = Math.max(leftMessage.size, rightMessage.size);
        return new SizeMessage(max, min, size, false);
    }


}
