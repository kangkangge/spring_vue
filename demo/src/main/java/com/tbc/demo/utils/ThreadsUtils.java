package com.tbc.demo.utils;


import java.util.concurrent.*;

public class ThreadsUtils {

    public static ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(40);
    private static final long WAIT_TIME = 10L;

    public static <T> T autoExecute(Object obj, String methodName, Object... params) {
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
        return t1 == null ? null : (V) t1;
    }
}
