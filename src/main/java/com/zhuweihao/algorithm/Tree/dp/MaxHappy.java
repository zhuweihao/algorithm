package com.zhuweihao.algorithm.Tree.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zhuweihao
 * @Date 2023/5/31 15:55
 * @Description com.zhuweihao.algorithm.class08
 */
public class MaxHappy {
    static class Employee {
        public int happy;
        public List<Employee> next;

        public Employee(int happy) {
            this.happy = happy;
            this.next = new ArrayList<>();
        }
    }

    static class Message {
        public int no;
        public int yes;

        public Message(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }

    public static int getMaxHappy(Employee boss) {
        if (boss == null) {
            return 0;
        }
        Message message = process(boss);
        return Math.max(message.no, message.yes);
    }

    public static Message process(Employee boss) {
        if (boss == null) {
            return new Message(0, 0);
        }
        int[] happy = {boss.happy, 0};
        boss.next.forEach(employee -> {
            Message message = process(employee);
            happy[1] += Math.max(message.yes, message.no);
            happy[0] += message.no;
        });
        return new Message(happy[1], happy[0]);
    }

    public static int maxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    // 当前来到的节点叫cur，
    // up表示cur的上级是否来，
    // 该函数含义：
    // 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    // 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    public static int process1(Employee cur, boolean up) {
        if (up) { // 如果cur的上级来的话，cur没得选，只能不来
            int ans = 0;
            for (Employee next : cur.next) {
                ans += process1(next, false);
            }
            return ans;
        } else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.next) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.next.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != getMaxHappy(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
