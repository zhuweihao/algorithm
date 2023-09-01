package com.zhuweihao.algorithm.并发编程;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @Author zhuweihao
 * @Date 2023/9/1 21:03
 * @Description com.zhuweihao.algorithm.并发编程
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConneciton());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long base = System.currentTimeMillis();
                long delay = mills;
                while (pool.isEmpty() && delay > 0) {
                    pool.wait(delay);
                    delay = base + mills - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
