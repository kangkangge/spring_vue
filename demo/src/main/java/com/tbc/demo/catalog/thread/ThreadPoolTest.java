package com.tbc.demo.catalog.thread;

import lombok.Data;

import java.util.Set;
import java.util.concurrent.BlockingQueue;

/**
 * 简单线程池
 */
@Data
public class ThreadPoolTest {
    //任务队列长度
    private int task;
    //工作队列长度
    private int work;
    //默认参数
    private int defTask = 3;
    private int defWork = 3;
    private Set<WorkThread> workQueue;
    private final BlockingQueue<Runnable> taskQueue;//阻塞有序队列存放任务

    public class WorkThread implements Runnable {
        private String name;

        @Override
        public void run() {
            try {
                Runnable take = taskQueue.take();
                System.out.println(take);
            } catch (Exception e) {
                System.out.println("执行失败" + e.getCause());
            }
        }
    }


}
