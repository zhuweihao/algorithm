package com.zhuweihao.algorithm.class02;

import java.util.Stack;

/**
 * @Author zhuweihao
 * @Date 2023/4/28 15:41
 * @Description com.zhuweihao.algorithm.class02
 */
public class TwoStackImplementQueue {
    private static class TwoStackQueue<T> {
        private Stack<T> stackPush;
        private Stack<T> stackPop;

        public TwoStackQueue() {
            this.stackPush = new Stack<T>();
            this.stackPop = new Stack<T>();
        }

        private void pushToPop() {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(T data) {
            stackPush.push(data);
            pushToPop();
        }

        public T poll() {
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            pushToPop();
            return stackPop.pop();
        }

        public T peek() {
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            pushToPop();
            return stackPop.peek();
        }
    }

    public static void main(String[] args) {
        TwoStackQueue<Integer> test = new TwoStackQueue<>();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        test.add(4);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
