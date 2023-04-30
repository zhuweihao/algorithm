package com.zhuweihao.algorithm.class02;

/**
 * @Author zhuweihao
 * @Date 2023/4/27 10:39
 * @Description com.zhuweihao.algorithm.class02
 */
public class RingArray {
    public static class MyQueue<T> {
        private T[] arr;
        private int pushIndex;
        private int pollIndex;
        private int currentSize;
        private int maxSize;

        public MyQueue(int maxSize) {
            arr = (T[]) new Object[maxSize];
            pushIndex = 0;
            pollIndex = 0;
            currentSize = 0;
            this.maxSize = maxSize;
        }

        private void push(T value) {
            if (currentSize == maxSize) {
                throw new RuntimeException("队列满了");
            }
            currentSize++;
            arr[pushIndex] = value;
            pushIndex = nextIndex(pushIndex);
        }

        private T pop() {
            if (currentSize == 0) {
                throw new RuntimeException("队列为空");
            }
            currentSize--;
            T result = arr[pollIndex];
            pollIndex = nextIndex(pollIndex);
            return result;
        }

        public boolean isEmpty() {
            return currentSize == 0;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nextIndex(int i) {
            return i < maxSize - 1 ? i + 1 : 0;
        }

    }
}
