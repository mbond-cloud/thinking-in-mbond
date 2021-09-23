package com.mbond.javase.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

/**
 * @description: StampedLock 实现一个缓存
 * 写锁、悲观读锁、乐观读
 * 乐观读时允许一个线程获取写锁进行写操作
 * 乐观读期间存在写操作，升级为悲观读
 * 写锁和悲观读锁不支持条件变量
 * 不支持重入
 * 使用 StampedLock 一定不要调用中断操作，如果需要支持中断功能，一定使用可中断的悲观读锁 readLockInterruptibly() 和写锁 writeLockInterruptibly()
 * @author: mbond
 * @date: 2021/9/22
 **/
public class MyCachePlus<K,V>{
    final Map<K,V> cache = new HashMap<>();
    final StampedLock sl = new StampedLock();

    //演示乐观读
    private int x,y;
    //悲观读
    long rstamp = sl.readLock();
    long wstamp = sl.writeLock();


    V get(K key){
        V v = null;
        try {
            v= cache.get(key);
        }finally {
            sl.unlock(rstamp);
        }
        return v;
    }

    V put(K key,V value){
        try {
            return cache.put(key,value);
        }finally {
            sl.unlock(wstamp);
        }
    }

    int cpu(){
        //乐观读
        long stamp = sl.tryOptimisticRead();
        int currx = x;
        int cyrry = y;
        if(!sl.validate(stamp)){
            stamp = sl.readLock();
            try {
                currx = x;
                cyrry = y;
                return x+y;
            }finally {
                sl.unlock(stamp);
            }
        }
        return x+y;
    }


}
