package com.mbond.javase.juc;

/**
 * @description: 类似转账的操作需要锁定多个资源
 * 方法一：锁定TransferAccount.class，全部串行，并发性能低
 * 方法二：所有TransferAccount绑定一个相同的对象，锁定该对象
 * synchronized锁定多个资源容易出现死锁
 * 获取不到锁直接进入阻塞状态，不能主动释放已经线程已经占有的资源
 * 例如：锁定A和B，锁定A成功，锁定B失败进入阻塞，但是此时A不会释放
 * @author: mbond
 * @date: 2021/9/22
 **/
public class TransferAccount {
    private int balance;
    void transfer(TransferAccount target,int amt){
        synchronized (this){
            synchronized (target){
                if(this.balance>=amt){
                    this.balance-=amt;
                    target.balance+=amt;
                }
            }
        }
    }

}
