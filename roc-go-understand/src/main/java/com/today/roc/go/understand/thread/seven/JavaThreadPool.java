package com.today.roc.go.understand.thread.seven;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月03日 00:54*
 * log.info()
 */
public class JavaThreadPool {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors返回的线程池对象的弊端如下：
//        1）FixedThreadPool和SingleThreadPool:
//  允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
//        2）CachedThreadPool:
//  允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。
//
//        Positive example 1：
//        //org.apache.commons.lang3.concurrent.BasicThreadFactory
//        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
//                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
//
//
//
//        Positive example 2：
//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
//                .setNameFormat("demo-pool-%d").build();
//
//        //Common Thread Pool
//        ExecutorService pool = new ThreadPoolExecutor(5, 200,
//                0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
//
//        pool.execute(()-> System.out.println(Thread.currentThread().getName()));
//        pool.shutdown();//gracefully shutdown
//
//
//
//        Positive example 3：
//    <bean id="userThreadPool"
//        class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
//        <property name="corePoolSize" value="10" />
//        <property name="maxPoolSize" value="100" />
//        <property name="queueCapacity" value="2000" />
//
//    <property name="threadFactory" value= threadFactory />
//        <property name="rejectedExecutionHandler">
//            <ref local="rejectedExecutionHandler" />
//        </property>
//    </bean>
//                //in code
//                userThreadPool.execute(thread);

    }
}
