package com.tbc.demo.catalog.asynchronization.course02.pool.create;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;


/**
 * 1.核心参数讲解与执行步骤
 */
@Slf4j
public class ThreadPool {

    /**
     * 1. 最简单线程池
     */
    ExecutorService executorService = Executors.newCachedThreadPool();

    @Test
    public void executorTest() throws ExecutionException, InterruptedException {
        /**
         * 使用submit 执行通过返回对象调用result.get()获取返回值;
         */
        Future<String> result = executorService.submit(() -> {

            return "submit==执行成功";
        });
        log.info(result.get());

        /**
         * 使用execute没有返回值;
         */
        executorService.execute(() -> log.info("测试_execute_执行,最简单线程池"));
        /**
         * 关闭线程池,关闭后需要重新初始化才可以使用
         */
        executorService.shutdown();

    }

    /**
     * 2.带配置的线程池
     */
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    /**
     * 这里我们设置线程池核心线程个数为当前物理机的CPU核数，最大线程个数为当前物理机CPU核数的2倍；设置线程池阻塞队列的大小为5；
     */
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutorDemo(AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        /**
         *  1.开启异步单元执行任务A
         *     1.如果正在运行的线程少于corePoolSize,将会创建一个线程来执行
         *     2.2.如果一个任务可以成功排队，那么我们仍然需要*再次检查是否应该添加线程*（因为现有线程自上次检查后就死掉了），或者*池自进入该方法后就关闭了。因此，我们*重新检查状态，并在必要时回滚排队，如果*停止，或者如果没有线程，则启动一个新线程
         *     3.如果我们无法将任务排队，则尝试添加一个新的线程。如果失败，我们知道我们已关闭或处于饱和状态*，因此拒绝该任务。
         */
        POOL_EXECUTOR.execute(() -> {
            try {
                doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 2.执行任务B
        doSomethingB();

        // 3.同步等待线程A运行结束
        System.out.println(System.currentTimeMillis() - start);

        // 4.挂起当前线程
        Thread.currentThread().join();
        //5.关闭线程池
        POOL_EXECUTOR.shutdown();
    }

    public static void doSomethingA() {
        log.info("A_执行");
    }

    public static void doSomethingB() {
        log.info("B_执行");
    }
}

