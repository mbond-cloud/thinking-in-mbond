package com.mbond.javase.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @description: StampedLock 实现一个缓存
 * 写锁、悲观读锁、乐观读
 * @author: mbond
 * @date: 2021/9/22
 **/
public class MyCachePlus<K,V>{
    final Map<K,V> cache = new HashMap<>();
    final StampedLock sl = new StampedLock();
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

}
