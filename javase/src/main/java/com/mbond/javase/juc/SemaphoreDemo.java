package com.mbond.javase.juc;

import java.util.concurrent.Semaphore;

/**
 * @description: 信号量实现的互斥
 * acquire+release原子操作+队列
 * 信号量和管程对比：
 * 1、信号量没有Condtion概念
 * 2、只能让一个阻塞线程被唤醒
 * 3、允许多个线程访问临界资源
 * @author: mbond
 * @date: 2021/9/22
 **/
public class SemaphoreDemo {
    private final Semaphore semaphore = new Semaphore(1);
    private int x=0;

    private void addX() throws InterruptedException {
        semaphore.acquire();
        try {
            int index=0;
            while(index++<1000){
                x++;
            }
        }finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo a = new SemaphoreDemo();
        Thread t1 = new Thread(()->{
            try {
                a.addX();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            try {
                a.addX();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a.x);
    }
}
