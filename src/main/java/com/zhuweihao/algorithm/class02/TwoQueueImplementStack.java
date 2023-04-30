package com.zhuweihao.algorithm.class02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author zhuweihao
 * @Date 2023/4/28 15:56
 * @Description com.zhuweihao.algorithm.class02
 */
public class TwoQueueImplementStack {
    public static class TwoQueueStack<T> {
        private Queue<T> queue1;
        private Queue<T> queue2;

        public TwoQueueStack() {
            this.queue1 = new LinkedList<>();
            this.queue2 = new LinkedList<>();
        }

        public void push(T data) {
            queue1.add(data);
        }

        public T pop() {
            while (queue1.size() > 1) {
                queue2.add(queue1.poll());
            }
            T result = queue1.poll();
            Queue<T> tmp = queue2;
            queue2 = queue1;
            queue1 = tmp;
            return result;
        }

        public T peek() {
            while (queue1.size() > 1) {
                queue2.add(queue1.poll());
            }
            T result = queue1.poll();
            queue2.add(result);
            Queue<T> tmp = queue2;
            queue2 = queue1;
            queue1 = tmp;
            return result;
        }

        public boolean isEmpty(){
            return queue1.isEmpty();
        }
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.pop().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }
}
