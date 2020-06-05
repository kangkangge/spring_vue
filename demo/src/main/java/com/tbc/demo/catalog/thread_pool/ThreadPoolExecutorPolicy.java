package com.tbc.demo.catalog.thread_pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池执行策略
 * Created by Yohann on 15/5/11.
 */
public class ThreadPoolExecutorPolicy {


    public static class WaitPolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            final BlockingQueue<Runnable> queue = executor.getQueue();
            try {
                queue.put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
