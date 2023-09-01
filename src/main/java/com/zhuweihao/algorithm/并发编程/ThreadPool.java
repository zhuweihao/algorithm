package com.zhuweihao.algorithm.并发编程;

/**
 * @Author zhuweihao
 * @Date 2023/9/1 22:37
 * @Description com.zhuweihao.algorithm.并发编程
 */
public interface ThreadPool<Job extends Runnable> {
    //执行一个job，这个job需要实现Runnable
    void execute(Job job);
    //关闭线程池
    void shutdown();
    //增加工作者线程
    void addWorker(int num);
    //减少工作者线程
    void removeWorker(int num);
    //得到正在等待执行的任务数量
    int getJobSize();
}
