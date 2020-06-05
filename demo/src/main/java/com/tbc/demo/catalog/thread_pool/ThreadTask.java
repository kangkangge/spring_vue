package com.tbc.demo.catalog.thread_pool;

public class ThreadTask implements Runnable {
    private String threadName;
    private int taskNo;

    public ThreadTask(String threadName, int taskNo) {
        this.threadName = threadName;
        this.taskNo = taskNo;
    }

    @Override
    public void run() {
        for (int i = 0; i < taskNo; i++) {
            System.out.println("线程名称:" + threadName + "线程循环的值:" + taskNo);
        }
    }
}
