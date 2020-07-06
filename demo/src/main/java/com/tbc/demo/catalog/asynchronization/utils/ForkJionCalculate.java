package com.tbc.demo.catalog.asynchronization.utils;

import java.io.Serializable;
import java.util.concurrent.RecursiveTask;

/**
 * 测试 forkJoin框架
 * 1. 继承RecursiveTask 或者 RecursiveAction
 * RecursiveTask 有返回值
 * RecursiveAction无返回值
 */
public class ForkJionCalculate extends RecursiveTask implements Serializable {

    private static final long serialVersionUID = 2462375556031755900L;


    private long start;

    private long end;

    private static final long THRESHOLD = 10000L;//临界值

    public ForkJionCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJionCalculate left = new ForkJionCalculate(start, middle);
            left.fork();//拆分，并将该子任务压入线程队列
            ForkJionCalculate right = new ForkJionCalculate(middle + 1, end);
            right.fork();
            return null;
        }
    }
}
