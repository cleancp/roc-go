package com.tuling.concurrency.course10_aqs_other;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description：
 *
 * @author：roc.zou 2022/9/6 5:12 下午
 */
@Slf4j
public class SemaphoreLearn {

    static Semaphore windows = new Semaphore(3);

    static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            10, 50, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200));

    public static void main(String[] args) throws InterruptedException {
        // 窗口限流
         window();
        // 限流器
        //limit();
    }

    /**
    * @description: 限流器
    * @param: []
    * @return: void
    * @author: roc.zou
    * @date: 2022/9/6 6:35 下午
    */
    private static void limit() throws InterruptedException {
        for (;;){
            Thread.sleep(100);
            poolExecutor.execute(()->{
                try {
                    windows.acquire(1);
                    log.debug("{} exec  可用令牌： {}  ", Thread.currentThread().getName(), windows.availablePermits());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    windows.release(1);
                }
            });
        }
    }


    /**
    * @description: Semaphore是一个计数信号量,Semaphore经常用于限制获取资源的线程数量
    * @param: []
    * @return: void
    * @author: roc.zou
    * @date: 2022/9/6 6:35 下午
    */
    private static void window() {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    windows.acquire();
                    log.debug("{} get lock ", Thread.currentThread().getName());
                    Thread.sleep(1000);
                    log.debug("{} release lock ", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    windows.release();
                }
            }, "Thread "+i).start();
        }
    }
}
