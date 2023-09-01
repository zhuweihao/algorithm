package com.zhuweihao.algorithm.并发编程;

import javafx.concurrent.Worker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author zhuweihao
 * @Date 2023/9/1 22:39
 * @Description com.zhuweihao.algorithm.并发编程
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    private static final int MAX_WORKER_NUMBERS = 10;
    private static final int DEFAULT_WORKER_NUMBER = 5;

    private static final int MIN_WORKER_NUMBERS = 1;
    //工作列表，将会向里面插入工作
    private final LinkedList<Job> jobs = new LinkedList<>();
    //工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());
    //工作者线程的数量
    private int workNum = DEFAULT_WORKER_NUMBER;
    //线程编号生成
    private AtomicLong threadNum = new AtomicLong();

    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.add(job);
                jobs.notifyAll();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker :
                workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorker(int num) {
        synchronized (jobs) {
            if (num + this.workNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workNum;
            }
            initializeWorkers(num);
            this.workNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs) {
            if (num >= this.workNum) {
                throw new IllegalArgumentException("beyond workNum");
            }
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    class Worker implements Runnable {
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    //如果工作列表为空，wait
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {

                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}
