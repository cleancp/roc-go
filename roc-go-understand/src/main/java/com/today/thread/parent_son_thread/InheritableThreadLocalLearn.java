package com.today.thread.parent_son_thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * description：
 *
 * @author：roc.zou 2022/10/7 3:21 下午
 */
public class InheritableThreadLocalLearn {

    public static void main(String[] args) throws InterruptedException {

        final ThreadLocal<Span> inheritableThreadLocal=new InheritableThreadLocal<>();
        inheritableThreadLocal.set(new Span("xiexiexie"));
        //输出xiexiexie
        Span span1 = inheritableThreadLocal.get();
        System.out.println(span1.name);
        ExecutorService es= Executors.newFixedThreadPool(1);
        es.execute(()->{
            System.out.println("====1====");
            //输出 xiexiexie
            Span span2 = inheritableThreadLocal.get();
            System.out.println(span2.name);
            inheritableThreadLocal.set(new Span("qiqiqi"));
            //输出 qiqiqi
            span2 = inheritableThreadLocal.get();
            System.out.println(span2.name);
        });
        TimeUnit.SECONDS.sleep(1);

        inheritableThreadLocal.set(new Span("setsetset"));
        es.execute(()->{
            System.out.println("====2====");
            //输出qiqiqi
            Span span2 = inheritableThreadLocal.get();
            System.out.println(span2.name);
            inheritableThreadLocal.set(new Span("xxx"));
            //输出xxx
            span2 = inheritableThreadLocal.get();
            System.out.println(span2.name);
        });

        TimeUnit.SECONDS.sleep(1);
        System.out.println("====0====");
        //输出setsetset
        Span span = inheritableThreadLocal.get();
        System.out.println(span.name);

    }

    static class Span{
        public String name;
        public int age;
        public Span(String name){
            this.name=name;
        }
    }
}
