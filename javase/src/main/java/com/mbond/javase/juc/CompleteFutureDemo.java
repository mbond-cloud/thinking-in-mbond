package com.mbond.javase.juc;

import java.util.concurrent.CompletableFuture;

/**
 * @description: CompletableFuture
 * 使用时尽量指定线程池来使用
 * @author: mbond
 * @date: 2021/9/23
 **/
public class CompleteFutureDemo {
    public static void main(String[] args) {
        //默认线程池，根据CPU核数
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(()->{
            System.out.println("T1洗水壶");
            System.out.println("T1烧开水");
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(()->{
            System.out.println("T2洗茶壶");
            System.out.println("T2洗茶杯");
            System.out.println("T2拿茶叶");
            return "碧螺春";
        });
        CompletableFuture<String> f3 = f1.thenCombine(f2,(__,tf)->{
            System.out.println("T3:拿到茶叶:" + tf);
            System.out.println("T3:泡茶...");
            return "上茶:" + tf;
        });
        System.out.println(f3.join());
    }
}
