package com.mbond.javase.juc;

/**
 * @description:
 * 缓存——可见性问题
 * 编译优化——有序性问题
 * 线程切换——原子性问题
 * synchronized范围足够的话 以上三个问题都能解决
 * volatile只能解决有序性和可见性问题
 * @author: mbond
 * @date: 2021/9/22
 **/
public class ThreadNotSafe {

    private int x = 0;

    public void addX(){
        int index=0;
        while(index++<1000){
            x++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadNotSafe a = new ThreadNotSafe();
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
        //输出1000-2000之间的随机数
        System.out.println(a.x);
    }
}
