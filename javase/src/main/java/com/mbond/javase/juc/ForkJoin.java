package com.mbond.javase.juc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @description: fork/join框架
 * @author: mbond
 * @date: 2021/9/24
 **/
public class ForkJoin {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        Fibonacci fib = new Fibonacci(30);
        Integer result =  forkJoinPool.invoke(fib);
        System.out.println(result);
    }

    static class Fibonacci extends RecursiveTask<Integer>{
        final int n;
        public Fibonacci(int n){
            this.n = n;
        }
        @Override
        protected Integer compute() {
            if(n<=1){
                return n;
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();//拆分子任务
            Fibonacci f2 = new Fibonacci(n - 2);
            return f2.compute()+f1.join();//递归
        }
    }
}
