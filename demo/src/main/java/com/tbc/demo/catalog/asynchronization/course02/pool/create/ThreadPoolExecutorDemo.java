package com.tbc.demo.catalog.asynchronization.course02.pool.create;

import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Data
class ThreadPoolExecutorDemo extends ThreadPoolExecutor {
    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;
    private TimeUnit unit;
    private BlockingQueue<Runnable> workQueue;
    private RejectedExecutionHandler handler;

    /**
     * @param corePoolSize    保留在线程池中的线程数量,即使达到了休眠条件也不会关闭
     * @param maximumPoolSize 最多开启线程数量
     * @param keepAliveTime   超出核心线程池的线程保持活跃时间如果超出了这个时间就会关闭线程
     * @param unit            TimeUnit可以用来延时或者对时间粒度进行转换,下面有demo
     * @param workQueue       在线程全部占用的时候,任务等待队列
     * @param handler         线程拒绝策略,当线程全部被占用,并且阻塞队列满了的处理
     */
    public ThreadPoolExecutorDemo(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }


    public void testTimeUnit() {
        TimeUnit timeUnit = TimeUnit.HOURS;
        Long days = timeUnit.convert(1L, TimeUnit.DAYS); //一天有多少小时
        timeUnit = TimeUnit.MILLISECONDS;
        Long milliSeconds = timeUnit.convert(1L, TimeUnit.DAYS); //一天有多少毫秒
        timeUnit = TimeUnit.MINUTES;
        Long minites = timeUnit.convert(7L, TimeUnit.DAYS); //一周有多少分钟

        System.out.println(days);
        System.out.println(milliSeconds);
        System.out.println(minites);
    }

}
