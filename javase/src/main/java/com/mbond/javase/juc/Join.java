package com.mbond.javase.juc;

/**
 * @description: join实现线程等待
 * @author: mbond
 * @date: 2021/9/22
 **/
public class Join {

    public static void main(String[] args) throws InterruptedException {
        final int[] x = {1};
        Thread thread = new Thread(()->{
            x[0]++;
        });
        thread.start();
        //thread.join();
        //不加join输出1或者2，加join一定是2
        System.out.println(x[0]);
    }
}
