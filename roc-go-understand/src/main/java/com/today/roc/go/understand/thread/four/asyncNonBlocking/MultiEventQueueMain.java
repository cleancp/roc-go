package com.today.roc.go.understand.thread.four.asyncNonBlocking;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description  多线程场景
 * @createTime 2021年02月28日 10:11*
 * log.info()
 */
public class MultiEventQueueMain {

    static class Event {

    }

    /**
     * 最大处理数 队列最大容量 10
     */
    public        int               max        = 10;
    private final LinkedList<Event> eventQueue = Lists.newLinkedList();

    public MultiEventQueueMain(int max) {
        this.max = max;
    }

    /***
     * event请求进来
     */
    public void offer(Event event) {
        console("come 0");
        synchronized (eventQueue) {
            console("sync 1");
            while (eventQueue.size() > max) {
                console(" queue already full");
                try {
                    eventQueue.wait();
                    console("wait after 2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console(" the new event is submitted");
            eventQueue.addLast(event);
            //当队列为空，请求一个Event，此时通知可以消费了
            this.eventQueue.notifyAll();
        }
    }

    /**
     * 处理event请求
     */
    public void take() {
        synchronized (eventQueue) {
            while (CollectionUtils.isEmpty(eventQueue)) {
                try {
                    console(" deal  queue is empty");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            //当队列已满，处理一个，此时通知可以继续生产
            this.eventQueue.notifyAll();
            console("the event " + event + " is handled");
        }
    }

    public void console(String message) {
        System.out.printf(" %s  ： %s \n", Thread.currentThread().getName(), message);
    }

    public static void main(String[] args) {
        MultiEventQueueMain eventQueueMain = new MultiEventQueueMain(10);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (; ; ) {
                    eventQueueMain.offer(new MultiEventQueueMain.Event());
                }
            }, "producer"+i).start();
            new Thread(() -> {
                for (; ; ) {
                    eventQueueMain.take();
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "consumer"+i).start();
        }
    }
}
