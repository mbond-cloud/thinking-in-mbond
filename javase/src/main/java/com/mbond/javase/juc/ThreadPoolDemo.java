package com.mbond.javase.juc;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 线程池
 * @author: mbond
 * @date: 2021/9/23
 **/
public class ThreadPoolDemo {
    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(2);
        executor = Executors.newSingleThreadExecutor();
        executor = Executors.newSingleThreadScheduledExecutor();
        executor = Executors.newCachedThreadPool();
        executor = Executors.newScheduledThreadPool(1);
        executor = Executors.newWorkStealingPool();

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,5,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),new ThreadPoolExecutor.CallerRunsPolicy());
    }

    //自定义线程创建工厂，比如自定义线程名称
    //参考Executors.DefaultThreadFactory
    class MyThreadFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            return null;
        }
    }
}
