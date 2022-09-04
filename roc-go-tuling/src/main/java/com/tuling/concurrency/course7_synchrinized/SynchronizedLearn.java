package com.tuling.concurrency.course7_synchrinized;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2022年09月03日 12:16*
 * log.info()
 */
@Slf4j
public class SynchronizedLearn {

    public static void main(String[] args) {
        LockObj lockObj = new LockObj();
        testLockInstance(lockObj, "thread1", "thread2");

        LockObj lockObj2 = new LockObj();
        testLockInstance(lockObj2, "thread11", "thread22");

        lockStaticClass();

        // 锁同一对象都需要等待获取锁
        Object assignObj = new Object();
        lockAssignObj(lockObj, assignObj,"assign1", "assign2");
        lockAssignObj(lockObj2, assignObj,"assign11", "assign22");
    }

    private static void lockAssignObj(LockObj lockObj, Object o, String assign1, String assign2) {
        new Thread(assign1) {
            @Override
            public void run() {
                lockObj.lockAssignOtherObj(o);
            }
        }.start();

        new Thread(assign2) {
            @Override
            public void run() {
                lockObj.lockAssignOtherObj(o);
            }
        }.start();
    }

    private static void lockStaticClass() {
        new Thread("thread3"){
            @Override
            public void run() {
                LockObj.lockStaticMethod();
            }
        }.start();

        new Thread("thread4"){
            @Override
            public void run() {
                LockObj.lockClassObj();
            }
        }.start();
    }

    private static void testLockInstance(LockObj lockObj, String thread1, String thread2) {
        new Thread(thread1) {
            @Override
            public void run() {
                lockObj.lockThis();
            }
        }.start();

        new Thread(thread2) {
            @Override
            public void run() {
                lockObj.lockMethod();
            }
        }.start();
    }

    static class LockObj{

        public void lockAssignOtherObj(Object obj){
            synchronized (obj){
                logAndSleep("LockAssignOtherObj");
            }
        }

        public void lockThis(){
            synchronized (this){
                logAndSleep("lockThis");
            }
        }

        public synchronized void lockMethod(){
            logAndSleep("lockMethod");
        }

        public static synchronized void lockStaticMethod(){
            logAndSleep("lockStaticMethod");
        }

        public static void lockClassObj(){
            synchronized (LockObj.class){
                logAndSleep("lockClassObj");
            }
        }

        private static void logAndSleep(String logMsg) {
            log.info("{} {} ……", Thread.currentThread().getName() , logMsg);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("{} {} 释放……", Thread.currentThread().getName(), logMsg);
        }
    }
}
