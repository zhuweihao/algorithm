package com.zhuweihao.algorithm.GreedyAlgorithm;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * 输入正数数组costs、正数数组profits、正数K和正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明：每做完一个项目，马上获得的收益，可以支持你去做下一个项目，不能并行的做项目。
 * 输出：最后获得的最大钱数
 * <p>
 * 测试链接
 *
 * @Author zhuweihao
 * @Date 2023/6/2 20:15
 * @Description com.zhuweihao.algorithm.GreedyAlgorithm
 */
public class IPO {

    static class Project {
        public int cost;
        public int profits;

        public Project(int cost, int profits) {
            this.cost = cost;
            this.profits = profits;
        }
    }

    public static class MyComparator1 implements Comparator<Project> {

        @Override
        public int compare(Project o1, Project o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class MyComparator2 implements Comparator<Project> {

        @Override
        public int compare(Project o1, Project o2) {
            return o2.profits - o1.profits;
        }
    }

    public int findMaximizedCapital(int K, int M, int[] profits, int[] costs) {
        PriorityQueue<Project> costPriority = new PriorityQueue<>(new MyComparator1());
        PriorityQueue<Project> profitPriority = new PriorityQueue<>(new MyComparator2());
        int result = M;
        for (int i = 0; i < costs.length; i++) {
            costPriority.add(new Project(costs[i], profits[i]));
        }
        for (int j = 0; j < K; j++) {
            while (!costPriority.isEmpty() && costPriority.peek().cost <= result) {
                profitPriority.add(costPriority.poll());
            }
            if (Objects.nonNull(profitPriority.peek())) {
                result += profitPriority.poll().profits;
            }
        }
        return result;
    }
}
