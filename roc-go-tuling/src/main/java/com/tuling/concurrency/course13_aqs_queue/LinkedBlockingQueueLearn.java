package com.tuling.concurrency.course13_aqs_queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * description：
 *
 * @author：roc.zou 2022/9/29 8:10 下午
 */
@Slf4j
public class LinkedBlockingQueueLearn {

    static BlockingQueue bQueue = new LinkedBlockingDeque(5);

    public static void main(String[] args) {
        // add();
        // offer();
        // poll();
        // peek();
        // put();
        offer();
        take();
    }
    /**
     * take 方法的作用是获取并移除队列的头结点。如果执队列里无数据，则阻塞，直到队列里有数据
     */
    private static void take() {
        try {
            for (int i = 0; i < 6; i++) {
                Object take = bQueue.take();
                log.info(" take element : {}", take);
            }
            log.info(" bQueue : {}", bQueue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * put 方法的作用是插入元素。如果队列已满就无法继续插入,阻塞插入线程，直至队列空出位置
     */
    private static void put() {
        try {
            for (int i = 0; i < 6; i++) {
                bQueue.put(i);
            }
            log.info(" bQueue : {}", bQueue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void peek() {
        // 获取顶层元素
        offer();
        Object peek = bQueue.peek();
        log.info(" element : {}", peek);
        log.info(" bQueue : {}", bQueue);
    }

    private static void poll() {
        // 获取顶层元素并弹出
        offer();
        Object poll = bQueue.poll();
        log.info(" element : {}", poll);
        log.info(" bQueue : {}", bQueue);
    }

    private static void offer() {
        for (int i = 0; i < 6; i++) {
            bQueue.offer(i);
        }
        log.info(" bQueue : {}", bQueue);
    }

    private static void add() {
        // 元素超出容量 java.lang.IllegalStateException: Deque full
        for (int i = 0; i < 6; i++) {
            bQueue.add(i);
        }
        log.info(" bQueue : {}", bQueue);
    }


}
