package com.today.roc.go.understand.thread.高并发编程详解.four.booleanlock;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年02月28日 14:47*
 * log.info()
 */
public class BooleanLockTest {

    private final Lock lock = new BooleanLock();

    public void syncMethod() {
        syncMethod(0);
    }

    public void syncMethod(long mills) {
        //使用try finally 保证lock被回收
        try {
            lock.lock(mills);
            int nextInt = current().nextInt(10)+1;
            System.out.println(currentThread() + " get lock " + nextInt);
            TimeUnit.SECONDS.sleep(nextInt);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public List<Thread> getBlockThreads(){
        return lock.getBlockThreads();
    }

    public static void main(String[] args) throws InterruptedException{
        BooleanLockTest blt = new BooleanLockTest();
        //multiLock();
        //interruptAble(blt);
        getLockTimeout(blt);
        getBlockThreads(blt);
    }

    /**
     * 获取锁超时
     */
    private static void getLockTimeout(BooleanLockTest blt) throws InterruptedException {
        new Thread(()->{
            blt.syncMethod(0);
        },"T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(() -> {
            blt.syncMethod(1000);
        }, "T2");
        t2.start();
    }

    /**
     * interrupt able 可中断特性
     */
    private static void interruptAble(BooleanLockTest blt) throws InterruptedException {
        new Thread(()->{
            blt.syncMethod();
        },"T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(() -> {
            blt.syncMethod();
        }, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
        t2.interrupt();
    }

    /**
     * 多个线程争抢锁资源
     */
    private static void multiLock(BooleanLockTest blt) {
        IntStream.range(0, 10).mapToObj(v -> new Thread(blt::syncMethod)).forEach(Thread::start);
    }

    private static void getBlockThreads(BooleanLockTest blt) {
        Thread thread = new Thread(() -> {
            while (true) {
                List<Thread> threads = blt.getBlockThreads();
                String str = StringUtils.join(threads, ",");
                System.out.printf(" block thread ： %s \n", str);
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

}
