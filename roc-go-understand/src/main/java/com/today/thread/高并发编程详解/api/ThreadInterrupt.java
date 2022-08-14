package com.today.thread.高并发编程详解.api;

import java.util.concurrent.TimeUnit;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年02月27日 10:53*
 * log.info()
 */
public class ThreadInterrupt {


    public static void main(String[] args) throws InterruptedException {
        testInterrupt();
    }

    private static void testInterrupt() {
        //在可中断方法前就已经中断，此时再执行可中断方法是怎样的
        //擦除interrupt 标识复位
        System.out.println("1 has interrupt ? " + Thread.interrupted());
        Thread.currentThread().interrupt();
        //判断是否中断，不能擦除interrupt标识
        System.out.println("2 has interrupt ? " + Thread.currentThread().isInterrupted());
        try {
            //a
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            System.out.println(" has interrupt " + e.getMessage());
        }
        //a处执行可中断方法，所以此时已擦除interrupt标识，返回false
        System.out.println("3 has interrupt ? " + Thread.currentThread().isInterrupted());
    }

    private static Thread interrupted() throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                System.out.println(Thread.interrupted());
            }
        });
        t.setDaemon(true);
        t.start();
        TimeUnit.MILLISECONDS.sleep(2);
        t.interrupt();
        return t;
    }

    private static Thread interruptAble() throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 1 " + Thread.currentThread().isInterrupted());
                    //可中断方法
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " 被中断 ");
                }
            }
        });

        //设置为守护线程，JVM主线程跑完了因为没有非守护线程而自动退出
        t.setDaemon(true);
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(t.getName() + " 3 " + t.isInterrupted());
        t.interrupt();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(t.getName() + " 4 " + t.isInterrupted());
        return t;
    }
}
