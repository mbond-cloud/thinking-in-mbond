package com.mbond.javase.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: ReentrantLock实现无锁互斥
 * volatile+cas+队列实现
 * @author: mbond
 * @date: 2021/9/22
 **/
public class LockAndCondtion {
    private final Lock lock = new ReentrantLock();

    private int x=0;

    private void addX(){
        //利用了 volatile 相关的 Happens-Before 规则
        //T1的逻辑happen-before T1的unlock   顺序性原则
        //T1的unlock happen-before T2的lock volatile 原则
        //T1的逻辑  happen-before T2的lock   传递性原则
        //lock.lock();
        try {
            int index=0;
            while(index++<1000){
                x++;
            }
        }finally {
            lock.unlock();
        }
    }

    private void addX2(){
        if(lock.tryLock()){
            try {
                int index=0;
                while(index++<1000){
                    x++;
                }
            }finally {
                lock.unlock();
            }
        }else{
            System.out.println("tryLock失败");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        LockAndCondtion a = new LockAndCondtion();
        Thread t1 = new Thread(()->{
            a.addX2();
        });
        Thread t2 = new Thread(()->{
            a.addX2();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a.x);
    }
}
