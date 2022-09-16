package com.tuling.concurrency.course12_aqs_read_write_lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * description：
 *
 * @author：roc.zou 2022/9/14 7:12 下午
 */
@Slf4j
public class ReadWriteLockLearn {

    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockLearn learn = new ReadWriteLockLearn();
        // 读写
        //testRW(learn);
        // 读读
        //testRR(learn);
        // 写写
        testWW(learn);
    }

    private static void testWW(ReadWriteLockLearn learn) throws InterruptedException {
        new Thread(()->{
            learn.write();
        },"Thread A").start();
        Thread.sleep(100);
        new Thread(()->{
            learn.write();
        },"Thread B").start();
    }

    private static void testRR(ReadWriteLockLearn learn) throws InterruptedException {
        new Thread(()->{
            learn.read();
        },"Thread A").start();
        Thread.sleep(100);
        new Thread(()->{
            learn.read();
        },"Thread B").start();
    }

    private static void testRW(ReadWriteLockLearn learn) throws InterruptedException {
        new Thread(()->{
            learn.read();
        },"Thread A").start();
        Thread.sleep(100);
        new Thread(()->{
            learn.write();
        },"Thread B").start();
    }

    public void read(){
        log.info(" {}  read  prepare get lock", Thread.currentThread().getName());
        r.lock();
        try{
            log.info(" {} get read lock , exec read ", Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.info(" {} release read lock ", Thread.currentThread().getName());
            r.unlock();
        }
    }

    public void write(){
        log.info(" {}  write  prepare get lock", Thread.currentThread().getName());
        w.lock();
        try{
            log.info(" {} get write lock , exec write ", Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.info(" {} release write lock ", Thread.currentThread().getName());
            w.unlock();
        }
    }

}
