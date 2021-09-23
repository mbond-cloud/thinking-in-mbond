package com.mbond.javase.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 生产者——消费者模型
 * 多个线程之间互相等待
 * t1和t2各执行一次后，唤醒主线程执行，如此往复
 * @author: mbond
 * @date: 2021/9/23
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        AtomicInteger y= new AtomicInteger();
        Executor executor = Executors.newFixedThreadPool(1);
        CyclicBarrier cb = new CyclicBarrier(2,()->{
            executor.execute(()->{
                System.out.println("主线程第"+ y.getAndIncrement() +"执行");
            });
        });
        Thread t1 = new Thread(()->{
            int x=0;
            try {
                while (true){
                    Thread.sleep(5000);
                    System.out.println("t1线程第"+x+++"执行");
                    cb.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Thread t2 = new Thread(()->{
            int x=0;
            try {
                while (true){
                    Thread.sleep(5000);
                    System.out.println("t2线程第"+x+++"执行");
                    cb.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t2.start();
    }
}
