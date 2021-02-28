package com.today.roc.go.understand.thread.api;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年02月27日 17:26*
 * log.info()
 */

public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        //join();
        testJoinInterrupt();
    }

    private static void join() throws InterruptedException {
        List<Thread> threads = IntStream.range(1, 3).mapToObj(ThreadJoin::createThread).collect(Collectors.toList());
        threads.forEach(Thread::start);
        for (Thread t : threads) {
            //主线程中join两个线程，会使主线程阻塞，直到两个线程都执行完成
            t.join();
        }
        for (int i = 0; i < 10; i++) {
            shortSleep();
            System.out.println(Thread.currentThread().getName() + "#" + i);
        }
    }

    private static Thread createThread(int seq) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shortSleep();
                System.out.println(Thread.currentThread().getName() + "#" + i);
            }
        }, String.valueOf(seq));
        return t;
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ;
    }

    private static void testJoinInterrupt() {
        //在可中断方法前就已经中断，此时再执行可中断方法是怎样的
        //擦除interrupt 标识复位
        System.out.println("1 has interrupt ? " + Thread.interrupted());
        Thread.currentThread().interrupt();
        //判断是否中断，不能擦除interrupt标识
        System.out.println("2 has interrupt ? " + Thread.currentThread().isInterrupted());
        try {
            //a
            Thread thread = createThread(1);
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(" has interrupt " + e.getMessage());
        }
        //a处执行可中断方法，所以此时已擦除interrupt标识，返回false
        System.out.println("3 has interrupt ? " + Thread.currentThread().isInterrupted());
    }

}
