package com.today.thread.线程池;

import com.today.utils.date.DateUtil;

import java.util.Date;
import java.util.concurrent.*;

/**
 * Software License Declaration.
 * <p>
 * zhilingsd.com, Co,. Ltd.
 * Copyright © 2016 All Rights Reserved.
 * <p>
 * Copyright Notice
 * This documents is provided to zhilingsd contracting agent or authorized programmer only.
 * This source code is written and edited by zhilingsd Co,.Ltd Inc specially for financial
 * business contracting agent or authorized cooperative company, in order to help them to
 * install, programme or central control in certain project by themselves independently.
 * <p>
 * Disclaimer
 * If this source code is needed by the one neither contracting agent nor authorized programmer
 * during the use of the code, should contact to zhilingsd Co,. Ltd Inc, and get the confirmation
 * and agreement of three departments managers  - Research Department, Marketing Department and
 * Production Department.Otherwise zhilingsd will charge the fee according to the programme itself.
 * <p>
 * Any one,including contracting agent and authorized programmer,cannot share this code to
 * the third party without the agreement of zhilingsd. If Any problem cannot be solved in the
 * procedure of programming should be feedback to zhilingsd Co,. Ltd Inc in time, Thank you!
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年09月25日 13:58*
 * log.info()
 */
public class ThreadPoolMain {


    static class CallableThread implements Callable {

        @Override
        public Object call() throws Exception {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("exec call");
            return 100;
        }
    }

    static class NewThread implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "exec");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " " + DateUtil.getDate(new Date(), DateUtil.DATE_TIME));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class JoinThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + "exec");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " " + DateUtil.getDate(new Date(), DateUtil.DATE_TIME));
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread() + e.getMessage());
                }
            }
        }
    }

    /**
     * 测试线程异常处理
     */
    static class ExceptionThread implements Runnable {
        @Override
        public void run() {
            Thread t = Thread.currentThread();
            System.out.println("run() by " + t);
            System.out.println("eh = " + t.getUncaughtExceptionHandler());
            throw new RuntimeException();
        }
    }

    /**
     * 后台线程：非后台线程跑完之后，后台线程会停止运行
     */
    static class DaemonThread implements Runnable {
        @Override
        public void run() {
            int count = 1;
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread() + " exec" + count++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 线程异常处理器
     */
    static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t);
            System.out.println("catch " + e);
            e.printStackTrace();
        }
    }

    /**
     * 线程工厂设置异常处理器
     */
    static class HandlerThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            return thread;
        }
    }


    /**
     * 线程工厂设置创建的线程都为后台线程
     */
    static class DaemonThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        }
    }


    public static void main(String[] args) {
        /**
         * long corePoolSize：核心线程数，线程池活跃的最小线程数(即使是空闲状态)， 如果设置了allowCoreThreadTimeOut，则会超时销毁
         * long maximumPoolSize：线程池最大线程数 Integer.MAX_VALUE
         * long keepAliveTime：当线程数大于核心数时，这是多余的空闲线程在终止之前等待新任务的最长时间
         * TimeUnit unit：keepAliveTime的单位
         * BlockingQueue<Runnable> workQueue：在执行任务之前用于保留任务的队列。该队列将仅保存由{@code execute}方法提交的{@code Runnable} 任务。
         */
        //cachedThreadPoolTest();
        //fixedThreadPoolTest();
        //forkJoinPoolTest();
        //scheduleThreadPoolTest();
        //线程异常捕获
        //catchThreadException();
        //后台线程
        //daemonThreadTest();
        //线程join 其它线程中间插入，导致当前线程停止，等插入线程执行完当前线程才执行
        //joinThreadTest();
        //Callable call方法调用
        //callableTest();

    }

    /**
     * Callable 执行call方法返回数据
     */
    private static void callableTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future future = executorService.submit(new CallableThread());
        try {
            Object o = future.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    /**
     * join插队
     */
    private static void joinThreadTest() {
        Thread t1 = new Thread(new JoinThread());
        Thread t2 = new Thread(new JoinThread());
        Thread t3 = new Thread(new JoinThread());
        try {
            /**
             join的意思是使得放弃当前线程的执行，并返回对应的线程，例如下面代码的意思就是：
             程序在main线程中调用t1线程的join方法，则main线程放弃cpu控制权，并返回t1线程继续执行直到线程t1执行完毕
             所以结果是t1线程执行完后，才到主线程执行，相当于在main线程中同步t1线程，t1执行完了，main线程才有执行的机会
             */
            t1.start();
            t1.join();
            t2.start();
            t3.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void daemonThreadTest() {
        ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i = 0; i < 3; i++) {
            executorService.execute(new DaemonThread());
        }
        try {
            //主线程睡眠5S之后 后台线程全部停止运行
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void scheduleThreadPoolTest() {
        // ScheduledThreadPoolExecutor(corePoolSize)
        // new ThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,new DelayedWorkQueue())
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);

        //new DelegatedScheduledExecutorService(new ScheduledThreadPoolExecutor(1))
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(new NewThread(), 5, TimeUnit.SECONDS);
    }

    private static void forkJoinPoolTest() {
        //new ForkJoinPool(Runtime.getRuntime().availableProcessors(), ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true)
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
    }

    /**
     * 线程异常捕获通过UncaughtExceptionHandler来处理
     */
    private static void catchThreadException() {
        ThreadFactory threadFactory = new HandlerThreadFactory();
        ExecutorService service = Executors.newCachedThreadPool(threadFactory);
        service.execute(new ExceptionThread());
    }

    /**
     * 核心线程数=线程池数
     * 使用有序阻塞队列，按照顺序执行
     * Executors.newSingleThreadExecutor() = Executors.newFixedThreadPool(1)
     */
    private static void fixedThreadPoolTest() {
        //new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            fixedThreadPool.execute(new NewThread());
        }
    }

    /**
     * 核心线程数=0
     * 线程池数=Integer.MAX_VALUE
     * 线程等待，60S等待任务执行时间
     * 同步队列
     */
    private static void cachedThreadPoolTest() {
        //new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>())
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            cachedThreadPool.execute(new NewThread());
        }
    }


}
