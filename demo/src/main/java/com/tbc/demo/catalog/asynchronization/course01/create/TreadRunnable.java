package com.tbc.demo.catalog.asynchronization.course01.create;

/**
 * 第一种方式: 实现Runable接口,实现run方法
 */
public class TreadRunnable implements Runnable {
    String threadName;

    @Override
    public void run() {
        System.out.println(threadName + ":线程运行了");
        threadName = "运行后的值";
    }

    public TreadRunnable(String threadName) {
        super();
        this.threadName = threadName;
    }
}
