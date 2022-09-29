package com.tuling.concurrency.course14_aqs_sync_queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * description：
 *
 * @author：roc.zou 2022/9/29 8:42 下午
 */
@Slf4j
public class SynchronousQueueLearn {

    /**
    * 默认 false 非公平 栈结构 先进后出 ， true 公平  先进先出 队列结构
    */
    final static BlockingQueue queue = new SynchronousQueue(true);

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->take(), "consumer1").start();
        Thread.sleep(10);
        new Thread(()->take(), "consumer2").start();

        Thread.sleep(100);

        new Thread(()->put(1), "producer1").start();
        Thread.sleep(10);
        new Thread(()->put(5), "producer2").start();
    }

    public static void take(){
        try {
            Object take = queue.take();
            log.info(" {} take value :{}", Thread.currentThread().getName(), take);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void put(Object value){
        try {
            queue.put(value);
            log.info(" {} put value :{}", Thread.currentThread().getName(), value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
