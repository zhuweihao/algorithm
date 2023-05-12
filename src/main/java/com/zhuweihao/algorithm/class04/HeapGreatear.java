package com.zhuweihao.algorithm.class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @Author zhuweihao
 * @Date 2023/5/12 17:09
 * @Description com.zhuweihao.algorithm.class04
 */
public class HeapGreatear {
    public static class Heap<T> {
        private ArrayList<T> heap;
        private HashMap<T, Integer> indexMap;
        private int heapSize;
        private Comparator<? super T> comparator;

        public Heap(Comparator<? super T> comparator) {
            this.comparator = comparator;
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
        }

        public T peek() {
            return heap.get(0);
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public void push(T data) {
            heap.add(data);
            heapInsert(heapSize);
            indexMap.put(data, heapSize++);
        }

        public T pop() {
            if (heapSize == 0) {
                return null;
            }
            T t = heap.get(0);
            swap(0, --heapSize);
            heap.remove(heapSize);
            indexMap.remove(t);
            heapify(0);
            return t;
        }

        public void resign(T value) {
            Integer index = indexMap.get(value);
            heapInsert(index);
            heapify(index);
        }

        private void heapInsert(int index) {
            int root = (index - 1) / 2;
            while (comparator.compare(heap.get(index), heap.get(root)) < 0) {
                swap(index, root);
                index = root;
                root = (index - 1) / 2;
            }
        }

        private void swap(int i, int j) {
            T t1 = heap.get(i);
            T t2 = heap.get(j);
            heap.set(i, t2);
            heap.set(j, t1);
            indexMap.put(t1, j);
            indexMap.put(t2, i);
        }

        private void heapify(int root) {
            int left = 2 * root + 1;
            int right = left + 1;
            while (left < heapSize) {
                int best = right < heapSize && comparator.compare(heap.get(right), heap.get(left)) < 0 ? right : left;
                best = comparator.compare(heap.get(best), heap.get(root)) < 0 ? best : root;
                if (best == root) {
                    break;
                }
                swap(root, best);
                root = best;
                left = 2 * root + 1;
                right = left + 1;
            }
        }
    }

    public static void main(String[] args) {
        // 展示非基础类型的用法
        Student s1 = new Student(17, "A同学");
        Student s2 = new Student(10, "B同学");
        Student s3 = new Student(29, "C同学");
        Student s4 = new Student(33, "D同学");
        Student s5 = new Student(54, "E同学");
        Student s6 = new Student(93, "F同学");
        // 生成一个加强堆
        // 排序策略是年龄小的放在堆顶，年龄小根堆
        Heap<Student> heap1 = new Heap<>((a, b) -> a.age - b.age);
        // 把所有学生加入堆
        heap1.push(s1);
        heap1.push(s2);
        heap1.push(s3);
        heap1.push(s4);
        heap1.push(s5);
        heap1.push(s6);
        // 加入之后
        // 可以把某个同学的年龄改了
        // 比如把s5，也就是E同学
        // 年龄从54改成了4
        s5.age = 4;
        // 此时堆被破坏了，因为你擅自改了一个同学的年龄
        // 只需要调用resign方法，就能让堆恢复成年龄小根堆
        // 而且复杂度是O(log N)，很快的
        // 系统提供的堆做不到的，加强堆可以
        heap1.resign(s5);
        // 依次弹出所有学生
        // 会发现从年龄小到年龄大依次弹出
        // 说明堆是正确的
        while (!heap1.isEmpty()) {
            Student cur = heap1.pop();
            System.out.println("年龄 : " + cur.age + " , 名字 : " + cur.name);
        }

        System.out.println("======================");

        // 现在展示非基础类型的加强堆用法
        int[] arr = {3, 3, 2, 5, 3};
        // arr[0] == 3
        // arr[1] == 3
        // arr[2] == 2
        // arr[3] == 5
        // arr[4] == 3
        // 每个位置的数字一定会自带一个下标，这是一定的!
        // 任何基础类型的元素，天生一定会自带一些类似下标的身份信息的！这是一定的！
        // 生成一个加强堆
        // 加强堆里只放下标即可，因为通过下标可以找到数字
        // 排序策略是 :
        // 数字小的下标，在堆顶
        Heap<Integer> heap2 = new Heap<>((i, j) -> arr[i] - arr[j]);

        // 把数组所有的下标加入堆
        // 就等于加入了所有数字
        heap2.push(0);
        heap2.push(1);
        heap2.push(2);
        heap2.push(3);
        heap2.push(4);

        // 加入之后
        // 可以把某个下标上的数字改了
        // arr[1]原来是3，现在变成了-9
        arr[1] = -9;
        // 此时堆被破坏了，因为你擅自改了一个下标的数字
        // 只需要调用resign方法，就能让堆恢复
        // 而且复杂度是O(log N)，很快的
        // 系统提供的堆做不到的，加强堆可以
        // 调用resign方法
        heap2.resign(1);

        // 依次弹出所有下标
        // 会发现下标上的数字越小，下标越早弹出
        // 说明堆是正确的
        while (!heap2.isEmpty()) {
            int curIndex = heap2.pop();
            System.out.println("下标 : " + curIndex + " , 数字 :" + arr[curIndex]);
        }
    }

    // 一个自己定义的非基础类型
    public static class Student {
        public int age;
        public String name;

        public Student(int a, String n) {
            age = a;
            name = n;
        }
    }
}
