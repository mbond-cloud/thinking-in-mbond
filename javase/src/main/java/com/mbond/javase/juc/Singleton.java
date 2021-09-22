package com.mbond.javase.juc;

/**
 * @description: double-check
 * 经典单例模式写法
 * volatile+最小范围的synchronized
 * @author: mbond
 * @date: 2021/9/22
 **/
public class Singleton {
    private static volatile Singleton singleton=null;

    private Singleton(){}

    public static Singleton getInstance(){
        if(singleton==null){
            synchronized (Singleton.class){
                if(singleton==null){
                    singleton =  new Singleton();
                }
            }
        }
        return singleton;
    }
}
