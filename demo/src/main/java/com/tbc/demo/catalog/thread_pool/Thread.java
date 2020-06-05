package com.tbc.demo.catalog.thread_pool;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * spring线程的使用
 */
public class Thread {


    public static void main(String[] args) {

        ThreadPoolTaskExecutor test = getExecutor(5, 50, 30, 2000, "test");
        test.initialize();
        for (int i = 0; i < 100; i++) {
            test.execute(new ThreadTask("线程" + i, 100000));
        }
    }


    /**
     * 配置线程池方法
     *
     * @param corePoolSize     线程池大小
     * @param maxPoolSize
     * @param queueCapacity
     * @param keepAliveSeconds
     * @param threadNamePrefix
     * @return
     */
    private static ThreadPoolTaskExecutor getExecutor(int corePoolSize, int maxPoolSize, int queueCapacity,
                                                      int keepAliveSeconds, String threadNamePrefix) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);//设置核心线程数
        executor.setMaxPoolSize(maxPoolSize);//设置最大线程数
        executor.setQueueCapacity(queueCapacity);//如果传入值大于0，底层队列使用的是LinkedBlockingQueue,否则默认使用SynchronousQueue
        executor.setKeepAliveSeconds(keepAliveSeconds);//除核心线程外的线程存活时间
        if (StringUtils.isNotBlank(threadNamePrefix)) {
            executor.setThreadNamePrefix(threadNamePrefix);//线程名称前缀
        }
        executor.setRejectedExecutionHandler(
                new ThreadPoolExecutorPolicy.WaitPolicy());//线程池对拒绝任务(无线程可用)的处理策略
        executor.setAllowCoreThreadTimeOut(true);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
}
