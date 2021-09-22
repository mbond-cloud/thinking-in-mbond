package com.mbond.javase.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @description:用 CountDownLatch 实现线程等待
 * 主线程依赖子线程的数据，子线程之间无依赖
 * @author: mbond
 * @date: 2021/9/22
 **/
public class CountDown {

    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(4);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        executor.execute(()->{
            System.out.println("子线程执行，准备主线程需要的数据");
            countDownLatch.countDown();
        });
        executor.execute(()->{
            System.out.println("子线程执行，准备主线程需要的数据");
            countDownLatch.countDown();
        });
        countDownLatch.await();
        System.out.println("主线程等待子线程全部执行完成后开始执行");
    }
}
