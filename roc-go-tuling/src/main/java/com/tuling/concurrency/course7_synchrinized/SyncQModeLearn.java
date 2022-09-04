package com.tuling.concurrency.course7_synchrinized;

import lombok.extern.slf4j.Slf4j;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2022年09月03日 22:44*
 * log.info()
 */
@Slf4j
public class SyncQModeLearn {

    final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        /**
         * ObjectMonitor
         * 默认模式，先进入等待队列cxq ，先进后出 （B先进，C先出）
         *
         * wait释放锁，线程进入waitSet等待，到时间后默认策略waitSet中的线程先进入EntityList，此时EntityList不为空，优先获取锁
         */
        // waitSet优先竞争
        boolean isWaitSetPriorityCompete = true;
        SyncQModeLearn learn = new SyncQModeLearn();
        new Thread(()->{learn.testCxq(isWaitSetPriorityCompete,300);},"A").start();
        Thread.sleep(50);
        new Thread(()->{learn.testCxq(false,500);},"B").start();
        Thread.sleep(50);
        new Thread(()->{learn.testCxq(false,200);},"C").start();
        Thread.sleep(50);
        new Thread(()->{learn.testCxq(false,0);},"D").start();
    }

    public void testCxq(boolean isWait,long mill){
        log.debug("{} get Lock before", Thread.currentThread().getName());
        synchronized (obj){
            log.debug("{} get Lock", Thread.currentThread().getName());
            try {
                if (isWait){
                    log.debug("{} wait", Thread.currentThread().getName());
                    obj.wait(mill);
                }else {
                    Thread.sleep(mill);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.debug("{} release Lock", Thread.currentThread().getName());
    }

}
