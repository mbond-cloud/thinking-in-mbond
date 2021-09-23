package com.mbond.javase.juc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @description: 线程安全集合
 * @author: mbond
 * @date: 2021/9/23
 **/
public class ThreadSafeCollections {

    public static void main(String[] args) {
        //非线程安全的集合
        List notSafeList = new ArrayList();
        notSafeList = new LinkedList();
        Map notSafeMap = new HashMap();
        //notSafeMap = new EnumMap();
        notSafeMap = new TreeMap();
        Set notSafeSet = new HashSet();
        notSafeSet = new TreeSet();
        Queue arrayQ = new PriorityQueue();
        arrayQ = new LinkedList();
        Deque dequeQ = new ArrayDeque();
        dequeQ = new LinkedList();
        //基于包装类实现的同步容器
        List list = Collections.synchronizedList(new ArrayList<>());
        //并发容器
        //List
        List copyList = new CopyOnWriteArrayList();//读多写非常少的场景，且能够容忍读写的短暂不一致
        //Map
        Map map = new ConcurrentHashMap<>();//key无序，kv不能为null
        Map skipMap = new ConcurrentSkipListMap();//key有序，kv不能为null
        //Set
        Set copySet = new CopyOnWriteArraySet();
        Set skipSet = new ConcurrentSkipListSet();
        //BlockingQueue
        BlockingQueue arrayQueue = new ArrayBlockingQueue(10);//有界
        BlockingQueue linkedQueue = new LinkedBlockingQueue();//有界
        BlockingQueue syncQueue = new SynchronousQueue();
        BlockingQueue linkedTransferQueue = new LinkedTransferQueue();
        BlockingQueue priorityQueue = new PriorityBlockingQueue();
        BlockingQueue delayQueue = new DelayQueue();
        //BlockingDeque
        BlockingDeque deque = new LinkedBlockingDeque();
        //no block Queue
        Queue noBlockQueue = new ConcurrentLinkedQueue();
        //no block Deque
        Queue noBlockDeque = new ConcurrentLinkedDeque();




    }
}
