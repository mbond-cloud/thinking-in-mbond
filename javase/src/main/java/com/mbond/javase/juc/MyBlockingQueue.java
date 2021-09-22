package com.mbond.javase.juc;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: Lock+Condtion实现阻塞队列
 * 这也是一种等待-通知机制
 * dubbo的异步转同步便是如此
 * @author: mbond
 * @date: 2021/9/22
 **/
public class MyBlockingQueue<T> {

    private int maxSize=10;
    private Queue queue = new ArrayDeque(10);

    final Lock lock = new ReentrantLock();
    //队列不满可入队
    final Condition notFull = lock.newCondition();
    //队列不空可出队
    final Condition notEmpty = lock.newCondition();

    void enq(T x) throws InterruptedException{
        lock.lock();
        try {
            //队列满了，等待
            while (queue.size()==maxSize){
                notFull.await();
            }
            //入队，通知出队
            queue.add(x);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    void deq() throws InterruptedException{
        lock.lock();
        try {
            //队列空了，等待
            while (queue.size()==0){
                notEmpty.await();
            }
            //出队，通知入队
            queue.remove();
            notFull.signalAll();
        }finally {
            lock.unlock();
        }
    }


}
