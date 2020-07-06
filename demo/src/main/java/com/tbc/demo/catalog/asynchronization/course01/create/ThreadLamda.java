package com.tbc.demo.catalog.asynchronization.course01.create;

/**
 * 扩展: lambda 方式快速创建匿名线程
 */
class ThreadLamda {

    public static void main(String[] args) {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        task.run();
        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Done!");
    }
}
