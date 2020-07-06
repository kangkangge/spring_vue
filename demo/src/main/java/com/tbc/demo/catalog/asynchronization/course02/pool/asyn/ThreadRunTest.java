package com.tbc.demo.catalog.asynchronization.course02.pool.asyn;

/**
 * 运行测试线程池
 */
public class ThreadRunTest {
    public static void main(String[] args) {
        DefaultThreadPool threadPool = new DefaultThreadPool();
        for (int i = 0; i <7 ; i++) {
            Job job = new Job();
            threadPool.execute(job);
        }
    }
}
