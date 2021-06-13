package com.today.roc.go.understand.thread.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月23日 17:16*
 * log.info()
 */
public class TestSelfLock {


    public static void main(String[] args) throws InterruptedException {
        new TestSelfLock().test();
    }

    public void test() throws InterruptedException {
        Lock lock = new SelfLock();
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (int i = 0; i < 20; i++) {
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        }
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
        }
    }
}
