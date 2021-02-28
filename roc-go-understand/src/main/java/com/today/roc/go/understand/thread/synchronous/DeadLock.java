package com.today.roc.go.understand.thread.synchronous;

import java.util.concurrent.TimeUnit;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年02月28日 00:49*
 * log.info()
 */
public class DeadLock {

    public static final String A = "a";
    public static final String B = "b";

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (A) {
                System.out.println(Thread.currentThread().getName() + "获取A锁");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println(Thread.currentThread().getName() + "获取B锁");
                }
            }
        }, "1");

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                System.out.println(Thread.currentThread().getName() + "获取B锁");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    System.out.println(Thread.currentThread().getName() + "获取A锁");
                }
            }
        }, "2");

        t1.start();
        t2.start();
    }

}
