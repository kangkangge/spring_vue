package com.tbc.demo.catalog.asynchronization.course01.create;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;


/**
 * 1.CompletableFuture 不阻塞异步调用
 */
public class CompletableFutureCreate {

    /**
     * 1. 无返回值第一个如果不传executor会使用默认的线程池
     * runAsync方法不支持返回值。
     * supplyAsync可以支持返回值。
     * 参数 executor 如果传了就用传的线程池如果没有使用默认的线程池
     */
    @Test
    public void create() throws ExecutionException, InterruptedException {
        //创建无返回值
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("这是无返回值"));
        //创建有返回值
        CompletableFuture<Long> longCompletableFuture = CompletableFuture.supplyAsync(() -> 999L);
        System.out.println("返回的数据" + longCompletableFuture.get());
    }

    /**
     * 2. 执行完毕后回调函数
     * <p>
     * public CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action)
     * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
     * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
     * public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn)
     * <p>
     * whenComplete 和 whenCompleteAsync 的区别：
     * whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
     * whenCompleteAsync：是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行。
     * <p>
     */
    @Test
    public void callback() {
        //有无返回值都想相同的方法
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("这是无返回值"));

        //成功
        future.whenComplete((t, action) -> System.out.println("成功执行完毕!"));
        //失败
        future.exceptionally((t) -> {
            System.out.println("执行失败！" + t.getMessage());
            return null;
        });
    }

    /**
     * 3. thenApply
     * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化
     * <p>
     * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
     * <p>
     * Function<? super T,? extends U>
     * T：上一个任务返回结果的类型
     * U：当前任务的返回值类型
     */

    @Test
    private static void thenApply() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                System.out.println("result1=" + result);
                return result;
            }
        }).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long t) {
                long result = t * 5;
                System.out.println("result2=" + result);
                return result;
            }
        });

        long result = future.get();
        System.out.println(result);
    }

    /**
     * handle 是执行任务完成时对结果的处理。
     * handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法。
     *
     * public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
     * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
     * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn,Executor executor);
     *
     * @throws Exception
     */
    @Test
    public static void handle() throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {

            @Override
            public Integer get() {
                int i= 10/0;
                return new Random().nextInt(10);
            }
        }).handle(new BiFunction<Integer, Throwable, Integer>() {
            @Override
            public Integer apply(Integer param, Throwable throwable) {
                int result = -1;
                if(throwable==null){
                    result = param * 2;
                }else{
                    System.out.println(throwable.getMessage());
                }
                return result;
            }
        });
        System.out.println(future.get());
    }
}

