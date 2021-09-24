package com.mbond.javase.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @description: fork/join框架
 * 统计单词数量
 * @author: mbond
 * @date: 2021/9/24
 **/
public class ForkJoinDemo {
    public static void main(String[] args) {
        String[] fc = {"hello world","hello me","hello fork","hello join","fork join in world"};
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        Mr mr = new Mr(fc,0,fc.length);
        Map<String, Long> result =  forkJoinPool.invoke(mr);  //输出结果
        result.forEach((k, v)->    System.out.println(k+":"+v));
    }

    static class Mr extends RecursiveTask<Map<String,Long>>{
        private String [] fc;
        private int start;
        private int end;
        public Mr(String [] fc,int from,int end){
            this.end=end;
            this.start=from;
            this.fc=fc;
        }

        @Override
        protected Map<String,Long> compute() {
            if(end-start==1){
                return calc(fc[start]);
            }else{
                int mid = (start+end)/2;
                Mr mr1 = new Mr(fc, start, mid);
                mr1.fork();
                Mr mr2 = new Mr(fc, mid, end);
                //计算子任务，并返回合并的结果
                return merge(mr2.compute(),mr1.join());
            }
        }
        private static Map<String, Long> merge(
                Map<String, Long> r1,
                Map<String, Long> r2) {
            Map<String, Long> result = new HashMap<>();
            result.putAll(r1);    //合并结果
            r2.forEach((k, v) -> {
                Long c = result.get(k);
                if (c != null)
                result.put(k, c+v);
                else
                result.put(k, v);
            });
            return result;
        }
        private static  Map<String,Long> calc(String line){
            Map<String,Long> map = new HashMap<>();
            String [] words = line.split("\\s+");
            for(String w:words){
                Long v = map.get(w);
                if(v!=null){
                    map.put(w,v+1);
                }else{
                    map.put(w,1L);
                }
            }
            return map;
        }
    }
}
