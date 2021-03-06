package com.tbc.demo.utils;


import java.util.concurrent.*;

/**callble
 * 线程池工具,解决Callable 创建大量实例类的问题!
 */
public class ThreadsUtils {

    public static ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(40);
    private static final long WAIT_TIME = 10L;

    public static <T> T autoExecute(Object obj, String methodName, Object... params) throws Exception {
        return (T) newFixedThreadPool.submit(AutoCallable.build(obj, methodName, params));
    }

    public static <V> V getFuture(Future<V> t) {
        V t1 = null;
        try {
            t1 = t.get(WAIT_TIME, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return t1 == null ? null : t1;
    }
}
