package com.today.thread.高并发编程详解.线程池.custhreadpool;

import com.google.common.collect.Lists;

import java.util.LinkedList;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月02日 00:11*
 * log.info()
 */
public class LinkedRunnableQueue implements RunnableQueue {

    /**
     * 队列任务最大数
     */
    private       int                  limit;
    /**
     * 任务集合
     */
    private final LinkedList<Runnable> runnableList = Lists.newLinkedList();

    /**
     * 拒绝策略
     */
    private DenyPolicy denyPolicy;

    /**
     * 线程池
     */
    private ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList) {
            //当前任务总数超出任务队列限制数，执行拒绝策略
            if (runnableList.size() > limit) {
                denyPolicy.reject(runnable, threadPool);
            } else {
                //任务队列新增任务
                runnableList.addLast(runnable);
                //通知线程池线程开始处理任务
                runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableList) {
            //队列中如果不存在可执行任务，则等待
            while (runnableList.isEmpty()) {
                try {
                    runnableList.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            //返回第一个
            return runnableList.removeFirst();
        }
    }

    @Override
    public int size() {
        //同步方法获取
        synchronized (runnableList) {
            return this.runnableList.size();
        }
    }
}
