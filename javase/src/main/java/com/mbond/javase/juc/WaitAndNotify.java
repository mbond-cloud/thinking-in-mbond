package com.mbond.javase.juc;

import java.util.List;

/**
 * @description: 等待——通知机制
 * synchronized +wait+notifyAll
 * 基本思路：获取锁——条件不满足——释放锁，阻塞——由其他线程通知
 * @author: mbond
 * @date: 2021/9/22
 **/
public class WaitAndNotify {
    private List<Object> als;

    synchronized void apply(Object from,Object to){
        while (als.contains(from)||als.contains(to)){
           try {
               wait();
           }catch (Exception e){}
        }
        als.add(from);
        als.add(to);
    }

    synchronized void free(Object from,Object to){
        als.remove(from);
        als.remove(to);
        notifyAll();
    }

}
