package com.mbond.javase.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @description: FutureTask 获取子线程的执行结果
 * 可以替代CountDownLanch
 * 任务内部可以互相等待，通过FutureTask.get
 * @author: mbond
 * @date: 2021/9/23
 **/
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
            return 1+2;
        });
        executorService.submit(futureTask);
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(()->{
            //futureTask2依赖futureTask的结果来执行
            int x = futureTask.get();
            return x+1+4;
        });
        executorService.submit(futureTask2);
        int r1 = futureTask.get();
        int r2 = futureTask2.get();
        System.out.println(r1+r2);
    }
}
