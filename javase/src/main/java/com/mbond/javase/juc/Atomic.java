package com.mbond.javase.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 原子操作类实现无锁并发
 * Atomic***开头的类
 * Atomic***Refrence   原子化的对象引用类型
 * Atomic***FieldUpdater的类 对象属性更新器
 * Atomic***Array  原子化数组
 * ***Accumulator累加器
 * @author: mbond
 * @date: 2021/9/23
 **/
public class Atomic {

    private AtomicInteger x= new AtomicInteger(0);

    private void addX(){
        int index=0;
        while(index++<1000){
            x.incrementAndGet();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Atomic a = new Atomic();
        Thread t1 = new Thread(()->{
            a.addX();
        });
        Thread t2 = new Thread(()->{
            a.addX();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a.x);
    }
}
