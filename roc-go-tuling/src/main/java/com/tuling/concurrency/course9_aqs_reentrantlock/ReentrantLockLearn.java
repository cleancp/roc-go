package com.tuling.concurrency.course9_aqs_reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2022年09月03日 10:42*
 * log.info()
 */
@Slf4j
public class ReentrantLockLearn {

    // 默认构造非公平锁
    static ReentrantLock lock = new ReentrantLock();
    private int sum ;
    static ReentrantLock fairLock = new ReentrantLock(true);

    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockLearn learn = new ReentrantLockLearn();
        // 同步执行
        // learn.syncExec();
        // 可重入
        // learn.reentrantAble();
        // 可中断
        // learn.interruptAble();
        // 锁超时
        // learn.lockOverrunTheTime();
        // 公平 非公平锁
        //learn.fairAndUnfairLock(fairLock);
        //learn.fairAndUnfairLock(lock);
        // 条件锁
        learn.condition();
    }

    public void condition(){
        new Thread(()->{
            lock.lock();
            try{
                log.debug("{} get lock" , Thread.currentThread().getName());
                log.debug("{} wait " , Thread.currentThread().getName());
                condition1.await();
                log.debug("{} continue " , Thread.currentThread().getName());
                log.debug("{} signal ThreadB condition2" , Thread.currentThread().getName());
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"Thread A").start();

        new Thread(()->{
            lock.lock();
            try{
                log.debug("{} get lock" , Thread.currentThread().getName());
                log.debug("{} signal ThreadA condition1" , Thread.currentThread().getName());
                condition1.signal();
                log.debug("{} wait " , Thread.currentThread().getName());
                condition2.await();
                log.debug("{} continue " , Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"Thread B").start();
    }

    public void  fairAndUnfairLock(ReentrantLock currLock) throws InterruptedException {
        // 一个线程拿到锁，其它线程按队列顺序准备好，公平锁按照顺序获取锁，非公平根据抢到的线程获取锁
        currLock.lock();
        try {
            log.debug("{} get lock" , Thread.currentThread().getName());
            for (int i = 0; i < 4; i++) {
                new Thread(()->{
                    currLock.lock();
                    try {
                        log.debug("{} get lock" , Thread.currentThread().getName());
                    }finally {
                        currLock.unlock();
                    }
                }, "Thread " +i).start();
                log.debug("Thread {} start" , i);
            }
            Thread.sleep(5000);
        }finally {
            currLock.unlock();
            log.debug("{} release lock" , Thread.currentThread().getName());
        }
    }

    public void lockOverrunTheTime(){
        Thread threadA = new Thread(() -> {
            try {
                log.debug("{} ready get Lock", Thread.currentThread().getName());
                //一直等待直到拿到锁，或者超过超时时间未获取锁 返回false
                boolean tryLock = lock.tryLock(5, TimeUnit.SECONDS);
                if (tryLock){
                    log.debug("{} get Lock", Thread.currentThread().getName());
                }else {
                    log.debug("{} get lock overrun the time", Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread A");

        lock.lock();
        try{
            log.debug("{} get lock", Thread.currentThread().getName());
            threadA.start();
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void syncExec() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                lock.lock();
                try{
                    for (int j = 0; j < 5000; j++) {
                        sum++;
                    }
                }finally {
                    lock.unlock();
                }

            },"线程"+i).start();
        }
        Thread.sleep(2000);
        log.debug("sum 值： {}", sum);
    }

    public void reentrantAble(){
        new Thread(()->{
            this.recursionReentrant(3);
        },"Thread A").start();
    }

    public void recursionReentrant(int recursionNum){
        lock.lock();
        try {
            log.debug("当前线程：{} 持有锁的次数 state：{}",Thread.currentThread().getName(), lock.getHoldCount());
            recursionNum--;
            if (recursionNum > 0){
                recursionReentrant(recursionNum);
            }
        }finally {
            lock.unlock();
        }
    }

    public void interruptAble(){
        Thread threadA = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                try {
                    log.debug("{} get lock", Thread.currentThread().getName());
                } finally {
                    lock.unlock();
                }
            } catch (Exception e) {
                log.error("{} 抛出异常 ", Thread.currentThread().getName(), e);
                e.printStackTrace();
            }
        }, "Thread A");

        lock.lock();
        try{
            log.debug("{} get lock", Thread.currentThread().getName());
            threadA.start();
            Thread.sleep(1000);
            // 发送中断
            threadA.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
