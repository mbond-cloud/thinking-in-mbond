package com.mbond.javase.juc;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

/**
 * @description: CompletionService
 * 3个任务异步执行
 * 有一个任务完成主线程开始操作
 * 主线程不依赖子线程的合集
 * @author: mbond
 * @date: 2021/9/23
 **/
public class CompletionServiceDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Executor executor = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        cs.submit(()->{
            Thread.sleep(2000);
            return 1;
        });
        cs.submit(()->{
            return 2;
        });
        cs.submit(()->{
            return 3;
        });
        for (int i=0; i<3; i++) {
            Integer r = cs.take().get();
            executor.execute(()->System.out.println(r));
        }
    }
}
