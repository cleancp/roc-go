package com.today.thread.高并发编程详解.线程池.custhreadpool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月02日 01:15*
 * log.info()
 */
public class DefaultThreadFactory implements ThreadFactory {

    public static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);

    public static final ThreadGroup group = new ThreadGroup("RocThreadPool-" + GROUP_COUNTER.getAndDecrement());

    public static final AtomicInteger COUNTER = new AtomicInteger(0);

    @Override
    public Thread createThread(Runnable runnable) {
        return new Thread(group, runnable, "thread-pool-" + COUNTER.getAndDecrement());
    }
}
