package com.today.thread.高并发编程详解.four.booleanlock;

import com.google.common.collect.Lists;
import com.today.utils.date.DateUtil;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description 自定义Boolean显示锁
 * @createTime 2021年02月28日 12:20*
 * log.info()
 */
public class BooleanLock implements Lock {

    /**
     * 当前获得锁的线程
     */
    private Thread currThread;

    /**
     * 锁标志
     */
    private boolean locked;

    /**
     * 存储阻塞的线程
     */
    private List<Thread> blockThreads = Lists.newArrayList();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (this.locked) {
                //已经被锁住，则当前线程进行等待
                final Thread tempThread = currThread();
                if (!blockThreads.contains(tempThread)) {
                    blockThreads.add(tempThread);
                }
                //同步的谁 谁才能wait
                console(" try lock  wait ");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    //如果当前线程wait时被中断，从blockThreads中移除，避免内存泄漏
                    blockThreads.remove(tempThread);
                    throw e;
                }
            }
            this.blockThreads.remove(currThread());
            this.locked = true;
            this.currThread = currThread();
            console(" try lock  success ");
        }
    }

    private Thread currThread() {
        return Thread.currentThread();
    }

    /**
     * mills 竞争锁 超时时间
     *
     * @param mills
     * @throws TimeoutException
     */
    @Override
    public void lock(long mills) throws TimeoutException, InterruptedException {
        synchronized (this) {
            if (mills <= 0) {
                lock();
            } else {
                long remainderMills = mills;
                long endMills = System.currentTimeMillis() + remainderMills;
                while (this.locked) {
                    final Thread tempThread = currThread();
                    if (remainderMills <= 0) {
                        this.blockThreads.remove(tempThread);
                        throw new TimeoutException(" can not get lock during "+mills+" ms");
                    } else {
                        //阻塞线程没在当前集合中 则添加
                        if (!this.blockThreads.contains(tempThread)) {
                            this.blockThreads.add(tempThread);
                        }
                        this.wait(remainderMills);
                        remainderMills = endMills - System.currentTimeMillis();
                    }
                    console(" try lock  wait remainderMills ：" + remainderMills);
                }
                this.blockThreads.remove(currThread());
                this.currThread = currThread();
                this.locked = true;
                console(" try lock  success ");
            }
        }
    }

    @Override
    public void unlock() {
        /**
         * 持有锁 才能解锁
         */
        synchronized (this) {
            //线程自己解自己的锁
            if (this.currThread == currThread()) {
                console(" release the lock ");
                this.locked = false;
                //通知其它线程去竞争锁
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockThreads() {
        return Collections.unmodifiableList(this.blockThreads);
    }

    public void console(String msg) {
        System.out.printf(" %s  ：%s ：%s \n ", currThread().getName(), DateUtil.getCurrentDateStr(), msg);
    }
}
