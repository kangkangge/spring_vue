package com.tbc.demo.catalog.callable;

import com.google.common.base.Supplier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试简单线程应用
 */
public class ThreadCoallbleTest {

    // 创建可灵活回收线程池 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
    public static final ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(40);
    public static final ExecutorService newFixedThreadPool_SHUAKE = Executors.newFixedThreadPool(8);

    public static void main(String[] args) {
        Future<Map<String, String>> user = newFixedThreadPool.submit(new TestCallble("张三", "2123123"));

    }

    /**
     * Callable 后面的泛型是指的返回的类型
     */
    private static class TestCallble implements Callable<Map<String, String>> {
        private String userNmae;
        private String password;

        public TestCallble(String userNmae, String password) {
            super();
            this.userNmae = userNmae;
            this.password = password;
        }

        @Override
        public Map<String, String> call() throws Exception {
            Map<String, String> map = new HashMap<>();
            map.put("use", "使用成功");
            map.put("userNmae", userNmae);
            map.put("password", password);
            return map;
        }
    }
}
