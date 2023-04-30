package com.zhuweihao.algorithm.class02;

import java.util.Objects;
import java.util.Stack;

/**
 * @Author zhuweihao
 * @Date 2023/4/28 11:22
 * @Description com.zhuweihao.algorithm.class02
 */
public class GetMinStack {

    public static class MyStack1 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack1() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int data) {
            if (stackData.isEmpty()) {
                stackMin.push(data);
            } else if (data < stackMin.peek()) {
                stackMin.push(data);
            }
            stackData.push(data);
        }

        public int pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("栈为空");
            }
            Integer pop = stackData.pop();
            if (Objects.equals(pop, stackMin.peek())) {
                stackMin.pop();
            }
            return pop;
        }

        public int getMin() {
            if (stackMin.isEmpty()) {
                throw new RuntimeException("栈为空");
            }
            return stackMin.peek();
        }

    }

    public static class MyStack2 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int data) {
            if (stackData.isEmpty()) {
                stackMin.push(data);
            } else {
                stackMin.push(data < stackMin.peek() ? data : stackMin.peek());
            }
            stackData.push(data);
        }

        public int pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("栈为空");
            }
            stackMin.pop();
            return stackData.pop();
        }

        public int getMin() {
            if (stackMin.isEmpty()) {
                throw new RuntimeException("栈为空");
            }
            return stackMin.peek();
        }
    }

    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());

        System.out.println("=============");

        MyStack2 stack2 = new MyStack2();
        stack2.push(3);
        System.out.println(stack2.getMin());
        stack2.push(4);
        System.out.println(stack2.getMin());
        stack2.push(1);
        System.out.println(stack2.getMin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getMin());
    }
}
