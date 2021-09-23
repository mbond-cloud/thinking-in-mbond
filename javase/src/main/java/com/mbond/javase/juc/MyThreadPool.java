package com.mbond.javase.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: 简单的线程池
 * @author: mbond
 * @date: 2021/9/23
 **/
public class MyThreadPool {
    //任务队列
    BlockingQueue<Runnable> workQueue;
    //工作线程
    List<WorkerThread> threads = new ArrayList<>();

    public MyThreadPool(int poolsize,BlockingQueue<Runnable> workQueue){
        this.workQueue = workQueue;
        for(int i=0;i<poolsize;i++){
            WorkerThread work = new WorkerThread();
            work.start();
            //threads.add(work);
        }
    }

    public void execute(Runnable r) throws InterruptedException {
        workQueue.put(r);
    }

    class WorkerThread extends Thread{
        @Override
        public void run() {
            while (true){
                Runnable task = null;
                try {
                    task = workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                task.run();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue workQueue = new LinkedBlockingQueue<>(2);
        // 创建线程池
        MyThreadPool pool = new MyThreadPool( 10, workQueue);// 提交任务
        pool.execute(()->{ System.out.println("hello1");});
        pool.execute(()->{ System.out.println("hello2");});
        pool.execute(()->{ System.out.println("hello3");});
        pool.execute(()->{ System.out.println("hello4");});
        pool.execute(()->{ System.out.println("hello5");});
        pool.execute(()->{ System.out.println("hello6");});
        pool.execute(()->{ System.out.println("hello7");});
        pool.execute(()->{ System.out.println("hello8");});
        pool.execute(()->{ System.out.println("hello9");});
        pool.execute(()->{ System.out.println("hello10");});
    }
}
