package com.today.thread.高并发编程详解.volatile关键字;

import java.util.concurrent.TimeUnit;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月07日 16:29*
 * log.info()
 */
public class VolatileFoo {

    private static final int MAX = 5;
    private static       int x   = 0;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            int local_value = x;
            while (local_value < MAX) {
                if (local_value != x) {
                    System.out.println(Thread.currentThread().getName()+" local_value =" + local_value + " x = " + x);
                    local_value = x;
                }
            }
        }, "reader").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            while (x < MAX) {
                System.out.println(Thread.currentThread().getName()+" x change = " + ++x);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "writer").start();

    }

}
