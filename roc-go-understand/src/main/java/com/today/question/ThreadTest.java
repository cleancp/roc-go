package com.today.question;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年06月29日 00:10*
 * log.info()
 */
public class ThreadTest {

    static Lock twolock = new ReentrantLock();
    static volatile int state = 0;

    static Lock threeLock = new ReentrantLock();
    static Condition Acon = threeLock.newCondition();
    static Condition Bcon = threeLock.newCondition();
    static Condition Ccon = threeLock.newCondition();

    static Semaphore Asemaphore = new Semaphore(1);
    static Semaphore Bsemaphore = new Semaphore(0);
    static Semaphore Csemaphore = new Semaphore(0);

    //三线程按顺序交替打印ABC的四种方法
    public static void main(String[] args) throws InterruptedException {
        //printMethodOne();
        //printMethodTwo();
        printMethodThree();
        //printMethodFour();
    }
    /**Semaphore 控制输出几次*/
    private static void printMethodFour() {
        //A  B  C
        FourMethodThread t1 = new FourMethodThread("A",Asemaphore,Bsemaphore);
        FourMethodThread t2 = new FourMethodThread("B",Bsemaphore,Csemaphore);
        FourMethodThread t3 = new FourMethodThread("C",Csemaphore,Asemaphore);
        t1.start();
        t2.start();
        t3.start();
    }

    /**condition 控制输出几次 控制 当前执行-下个释放*/
    private static void printMethodThree(){
        //A  B  C
        ThreeMethodThread t1 = new ThreeMethodThread("A",0,Bcon,Acon);
        ThreeMethodThread t2 = new ThreeMethodThread("B",1,Ccon,Bcon);
        ThreeMethodThread t3 = new ThreeMethodThread("C",2,Acon,Ccon);
        t2.start();
        t1.start();
        t3.start();
    }
    /**ReentrantLock*/
    private static void printMethodTwo() {
        //A  B  C
        TwoMethodThread t1 = new TwoMethodThread("A",0);
        TwoMethodThread t2 = new TwoMethodThread("B",1);
        TwoMethodThread t3 = new TwoMethodThread("C",2);
        t1.start();
        t2.start();
        t3.start();
    }
    /**synchronized wait*/
    private static void printMethodOne() throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        OneMethodThread t1 = new OneMethodThread("A",c,a);
        OneMethodThread t2 = new OneMethodThread("B",a,b);
        OneMethodThread t3 = new OneMethodThread("C",b,c);


        new Thread(t1).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(t2).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(t3).start();
        TimeUnit.SECONDS.sleep(1);
    }

    @AllArgsConstructor
    static class OneMethodThread implements Runnable{
        String name ;
        Object prev ;
        Object self ;

        @Override
        public void run() {
            while (true){
                synchronized (prev){
                    synchronized (self){
                        System.out.println(name);
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        self.notifyAll();
                    }
                    try {
                        prev.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @AllArgsConstructor
    static class TwoMethodThread extends Thread{
        String name ;
        final int bit;
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 5;) {
                try {
                    twolock.lock();
                    while (state % 3 == bit) {
                        System.out.println(name);
                        state ++ ;
                        TimeUnit.MILLISECONDS.sleep(500);
                        i++;
                    }
                }finally {
                    twolock.unlock();
                }
            }
        }
    }

    @AllArgsConstructor
    static class ThreeMethodThread extends Thread{
        String name ;
        final int bit;
        Condition signal;
        Condition await;
        @SneakyThrows
        @Override
        public void run() {
            try {
                threeLock.lock();
                for (int i = 0; i < 5;) {
                    //不等于自己就一直等待
                    while (state % 3 != bit) {
                        await.await();
                    }
                    System.out.println(name);
                    state ++ ;
                    TimeUnit.MILLISECONDS.sleep(500);
                    i++;
                    signal.signal();
                }
            }finally {
                threeLock.unlock();
            }
        }
    }

    @AllArgsConstructor
    static class FourMethodThread extends Thread{
        String name ;
        Semaphore acquire;
        Semaphore release;
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 5;i++) {
                acquire.acquire();//A 释放  B 释放 C 释放
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println(name);
                release.release(); //B获取  C 获取 A 获取
            }
        }
    }
}
