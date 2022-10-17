package com.tuling.concurrency.course1_speciality;

import com.tuling.concurrency._teacher.jucdemo.factory.UnsafeFactory;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.util.concurrent.locks.ReentrantLock;

/**
 * description：如何保证三大特性
 *
 * @author：roc.zou 2022/10/17 11:51 上午
 */
@Slf4j
public class KeepSpecialityLearn {

    volatile int count = 0;
    volatile boolean flag = true;
    Object obj = new Object();

    private final ReentrantLock lock = new ReentrantLock();

    private static final Unsafe UNSAFE;
    private static final long OFFSET;

    static {
        UNSAFE = UnsafeFactory.getUnsafe();
        OFFSET = UnsafeFactory.getFieldOffset(UNSAFE, KeepSpecialityLearn.class, "count");
    }

    public static void main(String[] args) {
        KeepSpecialityLearn learn = new KeepSpecialityLearn();
        // 可见性 synchronized, lock, volatile , 内存屏障 ，final关键字
        learn.visibilityLearn();
        // 原子性 synchronized, lock, cas
//        learn.atomicityLearn();
        // 有序性 ， 指令重排 , DCL 双重校验锁
        // 内存屏障, volatile , synchronized, lock
    }

    /**
     * @description: synchronized , lock , CAS
     * @param: []
     * @return: void
     * @author: roc.zou
     * @date: 2022/10/17 3:18 下午
     */
    private void atomicityLearn() {
        for (int j = 0; j < 10; j++) {
            Thread thread = new Thread(() -> {
                // this.keepAtomicityCas();
                // this.keepAtomicitySync();
                this.keepAtomicityLock();
            });
            thread.start();
        }
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("count = " + count);
    }

    private void keepAtomicitySync() {
        synchronized (this) { // synchronized保证原子性
            for (int i = 0; i < 10000; i++) {
                count++;
            }
            log.info("{} 执行完毕", Thread.currentThread().getName());
        }
    }

    private void keepAtomicityCas() {
        for (int i = 0; i < 10000; i++) {
            UnsafeFactory.getUnsafe().getAndAddInt(this, OFFSET, 1); // cas 保证原子性
        }
        log.info("{} 执行完毕", Thread.currentThread().getName());
    }

    private void keepAtomicityLock() {
        lock.lock(); // ReentrantLock 保证原子性
        try {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
            log.info("{} 执行完毕", Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }


    /**
     * @description: volatile ， synchronized , lock
     * @param: []
     * @return: void
     * @author: roc.zou
     * @date: 2022/10/17 3:17 下午
     */
    public void visibilityLearn() {
        // 一个线程能否看到另一个线程对共享变量的修改
        new Thread(() -> {
            log.info(Thread.currentThread().getName() + "开始执行: count=" + count);
            while (flag) {
                count++;
                // UnsafeFactory.getUnsafe().storeFence(); // 内存屏障

                /*try {
                    Thread.sleep(1); //内存屏障
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

            }
            log.info(Thread.currentThread().getName() + "跳出循环: count=" + count);
        }, "A").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            flag = false;
            log.info("{} flag 置为 false", Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + " flag 置为 false");
        }, "B").start();
    }

    public void waitTime(Integer waitMillis) {
        long endTime = System.currentTimeMillis() + waitMillis;
        while (System.currentTimeMillis() < endTime) {
        }
    }

}
