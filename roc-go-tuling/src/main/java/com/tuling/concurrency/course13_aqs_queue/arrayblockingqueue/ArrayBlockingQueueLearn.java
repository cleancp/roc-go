package com.tuling.concurrency.course13_aqs_queue.arrayblockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
* @description:
* @author: roc.zou
* @date: 2022/9/29 6:11 下午
*/
public class ArrayBlockingQueueLearn {

    public static void main(String[] args) throws Exception {
        //使用ArrayBlockingQueue初始化一个BlockingQueue，指定容量的上限为1024
        BlockingQueue queue = new ArrayBlockingQueue(1024);
        
        Producer producer = new Producer(queue);  //生产者
        Consumer consumer = new Consumer(queue);  //消费者

        new Thread(producer).start();  //开启生产者线程
        new Thread(consumer).start();  //开启消费者线程

        Thread.sleep(4000);
    }
}

