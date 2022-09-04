package com.tuling.concurrency.course9_aqs_reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2022年09月04日 12:29*
 * log.info()
 */
@Slf4j
public class CustomAQSLock extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0 , arg)){
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setState(arg);
        setExclusiveOwnerThread(null);
        return true;
    }


    public void  lock(){
        acquire(1);
    }

    public void  unlock(){
        release(0);
    }

    static int sum = 0;

    public static void main(String[] args) throws InterruptedException {
        CustomAQSLock lock = new CustomAQSLock();
        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                //tryImpl(lock);
                aqsLockImpl(lock);
            },"Thread "+i).start();
        }
        Thread.sleep(2000);
        log.debug(" sum值 ：{}", sum);
    }

    private static void aqsLockImpl(CustomAQSLock lock) {
        lock.lock();
        try {
            log.debug("{} get lock", Thread.currentThread().getName());
            for (int j = 0; j < 5000; j++) {
                sum++;
            }
        }finally {
            lock.unlock();
        }
    }

    private static void tryImpl(CustomAQSLock lock) {
        while (true){
            if (lock.tryAcquire(1)){
                try{
                    log.debug("{} get lock", Thread.currentThread().getName());
                    for (int j = 0; j < 5000; j++) {
                        sum++;
                    }
                    return;
                }finally {
                    lock.tryRelease(0);
                }
            }
        }
    }

}
