package com.tbc.demo.catalog.asynchronization.course02.pool.create;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 1.线程池的创建与使用
 */
@Slf4j
public class ThreadPoolCreate {
    /**
     * jdk 线程池创建与使用
     */

    //获取运行jdk虚拟机的cpu数量
    private final int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    //JDK自带线程池
    private final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutorDemo(AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void testExcute() {
        POOL_EXECUTOR.execute(() -> {
            log.info("jdk线程池执行方法!");
        });
    }


    /**
     * spring线程池配置,实际配置的时候打开注解
     */
    // @Configuration
    //@EnableAsync
    //@Bean
    public Executor asyncServiceExecutor() {
        log.info("start asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(5);
        //配置最大线程数
        executor.setMaxPoolSize(5);
        //配置队列大小
        executor.setQueueCapacity(99999);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }


    @Test
    public void testExecutor() {
        Executor executor = asyncServiceExecutor();
        executor.execute(() -> {
            log.info("sprinng 线程池执行方法!");
        });
    }

}
