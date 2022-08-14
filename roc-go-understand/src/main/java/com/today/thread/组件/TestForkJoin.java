package com.today.thread.组件;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import sun.security.krb5.internal.crypto.dk.AesDkCrypto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collector;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月22日 20:46*
 * log.info()
 */
public class TestForkJoin {

    public static final int THROLD = 1000;
   //execute(ForkJoinTask) 异步执行tasks，无返回值
   //invoke(ForkJoinTask) 有Join, tasks会被同步到主进程
   //submit(ForkJoinTask) 异步执行，且带Task返回值，可通过task.get 实现同步到主线程
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> source = Lists.newArrayList();
        Integer sum = 0;
        for (int i = 0; i < 1000000; i++) {
            source.add(i);
            sum += i;
        }
        //System.out.println(sum);
        long start = System.currentTimeMillis();
//        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
//        ForkJoinSub sub = new ForkJoinSub(0, source.size(), source);
//        Integer invoke = forkJoinPool.invoke(sub);
//        System.out.println(invoke);

        ForkJoinPool forkJoinPool2 = ForkJoinPool.commonPool();
        List<Integer> countTwo = Lists.newArrayList();
        Integer total = 0;
        ForkJoinSubTwo subTwo = new ForkJoinSubTwo(0, source.size(), source, countTwo,total);
//        forkJoinPool2.invoke(subTwo);
        ForkJoinTask<Void> submit = forkJoinPool2.submit(subTwo);
        System.out.println("-----");
        submit.get();
        System.out.println(subTwo.getCount().stream().reduce(0,(o1,o2)->(Objects.isNull(o1)?0:o1)+(Objects.isNull(o2)?0:o2)));
        System.out.println(System.currentTimeMillis() - start);
    }

    @Data
    @AllArgsConstructor
    static class ForkJoinSubTwo extends RecursiveAction {
        private Integer       from;
        private Integer       to;
        private List<Integer> source;
        private List<Integer> count;
        private Integer total;

        @Override
        protected void compute() {
            if (to - from > THROLD) {
                int mid = (to + from) / 2;
                ForkJoinSubTwo left = new ForkJoinSubTwo(from, mid, source, count,total);
                ForkJoinSubTwo right = new ForkJoinSubTwo(mid, to, source, count,total);
                //int i = 1/0 ;
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                invokeAll(left, right);
            } else {
                Integer sum = 0;
                for (int i = from; i < to; i++) {
                    Integer v = source.get(i);
                    sum += v;
                }
                count.add(sum);
                total = total+sum;
                //int i = 1 / 0;
            }
        }
    }

    @AllArgsConstructor
    static class ForkJoinSub extends RecursiveTask<Integer> {
        private Integer       from;
        private Integer       to;
        private List<Integer> source;

        @Override
        protected Integer compute() {
            if (to - from > THROLD) {
                int mid = (to + from) / 2;
                ForkJoinSub left = new ForkJoinSub(from, mid, source);
                ForkJoinSub right = new ForkJoinSub(mid, to, source);
                invokeAll(left, right);
                return left.join() + right.join();
            } else {
                Integer sum = 0;
                for (int i = from; i < to; i++) {
                    Integer v = source.get(i);
                    sum += v;
                }
//                int a = 1/0;
                return sum;
            }
        }
    }
}
