package com.today.roc.go.understand.thread.高并发编程详解.线程池.custhreadpool;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description 任务缓存队列
 * @createTime 2021年03月01日 23:19*
 * log.info()
 */
public interface RunnableQueue {

    /**
     * 添加任务
     */
    void offer(Runnable runnable);

    /**
     * 获取任务
     */
    Runnable take() throws InterruptedException;

    /**
     * 获取任务队列中的数量
     */
    int size();
}
