package com.today.roc.go.understand.thread.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月23日 17:00*
 * log.info()
 */
public class SelfLock implements Lock {

    /**
     * state = 1 获取到锁
     * state = 0 无对象拿到锁
     */
    static class Sync extends AbstractQueuedSynchronizer {
        /**
         * 独占锁 共享锁
         *
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
            //return getExclusiveOwnerThread() == Thread.currentThread();
        }

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, arg)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new RuntimeException("");
            }
            setExclusiveOwnerThread(null);
            //独占锁 当前线程已经拿到锁 只能当前线程才能释放
            setState(0);
            return true;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(0);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
