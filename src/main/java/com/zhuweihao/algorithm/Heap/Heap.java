package com.zhuweihao.algorithm.Heap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;

import static com.zhuweihao.algorithm.utils.IntUtil.swap;

/**
 * @Author zhuweihao
 * @Date 2023/5/12 11:07
 * @Description com.zhuweihao.algorithm.class04
 */
public class Heap {

    public static class MaxRootHeap {
        private int[] heap;
        private final int maxSize;

        private int size;

        public MaxRootHeap(int maxSize) {
            this.maxSize = maxSize;
            heap = new int[maxSize];
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == maxSize;
        }

        public void push(int data) throws Exception {
            if (size == maxSize) {
                throw new Exception("heap is full");
            }
            heap[size] = data;
            heapInsert(heap, size++);
        }

        /*
        logN
         */
        private void heapInsert(int[] heap, int index) {
            int root = (index - 1) / 2;
            //注意：-1/2=0，故此处不需要对数组越界进行判断
            while (heap[index] > heap[root]) {
                swap(heap, index, root);
                index = root;
                root = (index - 1) / 2;
            }
        }

        public int pop() throws Exception {
            if (size == 0) {
                throw new Exception("heap is empty");
            }
            int max = heap[0];
            swap(heap, 0, --size);
            heapify(heap, 0, size);
            return max;
        }

        /*
        logN
         */
        private void heapify(int[] heap, int root, int size) {
            int left = 2 * root + 1;
            int right = left + 1;
            while (left < size) {
                int maxOfLeftAndRight = right < size && heap[left] < heap[right] ? right : left;
                int max = heap[maxOfLeftAndRight] > heap[root] ? maxOfLeftAndRight : root;
                if (max == root) {
                    break;
                }
                swap(heap, root, max);
                root = maxOfLeftAndRight;
                left = 2 * root + 1;
                right = left + 1;
            }
        }
    }

    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }

    public static void main(String[] args) throws Exception {
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MaxRootHeap my = new MaxRootHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

        //java实现的堆结构，默认小根堆
        PriorityQueue<Integer>  heap=new PriorityQueue<>();
    }

}
