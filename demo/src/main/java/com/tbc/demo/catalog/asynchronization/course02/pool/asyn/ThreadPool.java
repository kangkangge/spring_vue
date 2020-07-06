package com.tbc.demo.catalog.asynchronization.course02.pool.asyn;

/**
 * 定义线程池接口
 */
interface ThreadPool {
    // 执行一个Job，这个Job需要实现Runnable
    void execute(Runnable job);

    // 关闭线程池
    void shutdown();

    // 增加工作者线程
    void addWorkers(int num);

    // 减少工作者线程
    void removeWorker(int num);

    // 得到正在等待执行的任务数量
    int getJobSize();
}
