package com.tuling.concurrency.course7_synchrinized;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author Fox
 * 测试 偏向锁，轻量级锁，重量级锁标记变化
 * 关闭延迟开启偏向锁： -XX:BiasedLockingStartupDelay=0
 * 无锁 001
 * 偏向锁 101
 * 轻量级锁 00
 * 重量级锁 10
 */
@Slf4j
public class BaisedLockLearn {

    public static void main(String[] args) throws InterruptedException {
        Object obj =testBiasedLockingDelay();
        // testLockUp(BaisedLockDemo.class);
         testLockUp(obj);
        // printLayout("最后输出结果", obj);
    }

    private static void testLockUp(Object obj) throws InterruptedException {
         thread1Compete(obj); // thread1此时是偏向锁，偏向线程ID变更为thread1关联的线程ID
        //控制线程竞争时机
        // Thread.sleep(1); 设置太小，多个线程同时竞争一把锁，会直接升级为重量级锁
         Thread.sleep(100); //睡眠时间为，当Thread1执行完，Thread2再去竞争时，升级为轻量级锁
         thread2Compete(obj);
         //thread3Compete(obj);
    }

    private static void thread1Compete(Object obj) {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                printLayout(Thread.currentThread().getName()+"开始执行。。。\n", obj);
                synchronized (obj){
                    // 思考：偏向锁执行过程中，调用hashcode会发生什么？
                     //testHashCodeInfluence(obj); // 升级为重量级锁，将hashCode数据存储到指向Monitor指针
//                    //obj.notify();
                     testWaitInfluence(obj, 50);
                    printLayout(Thread.currentThread().getName()+"获取锁执行中。。。\n", obj);
                }
                printLayout(Thread.currentThread().getName()+"释放锁。。。\n", obj);
            }
        },"thread1").start();
    }

    private static void thread2Compete(Object obj) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                printLayout(Thread.currentThread().getName()+"开始执行。。。\n", obj);
                synchronized (obj){
                    printLayout(Thread.currentThread().getName()+"获取锁执行中。。。\n", obj);
                }
                printLayout(Thread.currentThread().getName()+"释放锁。。。\n", obj);
            }
        },"thread2").start();
    }

    private static void thread3Compete(Object obj) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                printLayout(Thread.currentThread().getName()+"开始执行。。。\n", obj);
                synchronized (obj){
                    printLayout(Thread.currentThread().getName()+"获取锁执行中。。。\n", obj);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printLayout(Thread.currentThread().getName()+"释放锁。。。\n", obj);
            }
        },"thread3").start();
    }

    /**
     * 偏向延迟
     * @throws InterruptedException
     */
    private static Object testBiasedLockingDelay() throws InterruptedException {
        printLayout("延时前新对象", new Object());
        //HotSpot 虚拟机在启动后有个 4s 的延迟才会对每个新建的对象开启偏向锁模式
        Thread.sleep(4500);
        Object obj = new Object();
         testHashCodeInfluence(obj);
        // testWaitInfluence(obj);
        printLayout("延时后新对象", obj);
        return obj;
    }

    private static void testWaitInfluence(Object obj, long timeMs) throws InterruptedException {
        // 应当是拿到锁的对象才能调用 ， 未拿到锁报错：java.lang.IllegalMonitorStateException
        obj.wait(timeMs);
        // 抛出的异常表明某一线程已经试图等待对象的监视器，或者试图通知其他正在等待对象的监视器而本身没有指定监视器的线程
        // 简单的来说，当你在调用notify(), notifyAll(),wait(), wait(long), wait(long, int)等线程控制操作方法时，必须要有两个前提。
        // 第一：必须要在被synchronized关键字控制的同步代码块中，才能调用这些方法。第二，调用者必须为你当前的锁对象。
        printLayout("调用wait后", obj);
    }

    private static void testNotifyInfluence(Object obj) throws InterruptedException {
        // Illegal：非法的
        // 应当是拿到锁的对象才能调用 ， 未拿到锁报错：java.lang.IllegalMonitorStateException
        obj.notify();
        // 抛出的异常表明某一线程已经试图等待对象的监视器，或者试图通知其他正在等待对象的监视器而本身没有指定监视器的线程
        // 简单的来说，当你在调用notify(), notifyAll(),wait(), wait(long), wait(long, int)等线程控制操作方法时，必须要有两个前提。
        // 第一：必须要在被synchronized关键字控制的同步代码块中，才能调用这些方法。第二，调用者必须为你当前的锁对象。
        printLayout("调用notify后", obj);
    }

    /**
     * hashCode对偏向影响
     * 如果对象调用了hashCode,还会开启偏向锁模式吗？
     */
    private static void testHashCodeInfluence(Object obj){
        // 思考： 如果对象调用了hashCode,还会开启偏向锁模式吗
        obj.hashCode();
        printLayout("调用hashCode后", obj);
    }

    private static void printLayout(String msgTip, Object obj){
        log.debug("{}：{}", msgTip, ClassLayout.parseInstance(obj).toPrintable());
    }
}