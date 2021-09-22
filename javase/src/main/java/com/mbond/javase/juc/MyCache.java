package com.mbond.javase.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: ReadWriteLock实现一个缓存
 * 读多写少的场景
 * 支持锁降级：写锁——>读锁
 * 只有写锁支持条件变量，因此读是不互斥的
 * @author: mbond
 * @date: 2021/9/22
 **/
public class MyCache<K,V>{
    final Map<K,V> cache = new HashMap<>();
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    Lock r = rwl.readLock();
    Lock w = rwl.writeLock();

    V get(K key){
        V v = null;
        r.lock();
        try {
            v= cache.get(key);
        }finally {
            r.unlock();
        }
        //缓存存在，直接返回
        if(v!=null){
            return v;
        }
        //缓存不存在，重新查询放进缓存
        w.lock();
        try {
            v = cache.get(key);
            if(v==null){
                //v=查询回来的值
                v = cache.put(key,v);
            }
        }finally {
            w.unlock();
        }
        return v;
    }

    V put(K key,V value){
        w.lock();
        try {
            return cache.put(key,value);
        }finally {
            w.unlock();
        }
    }

}
