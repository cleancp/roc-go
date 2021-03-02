package com.today.roc.go.understand.thread.seven.custhreadpool;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description 线程池
 * @createTime 2021年03月01日 23:14*
 * log.info()
 */
public interface ThreadPool {
    /**
     * 提交任务
     *
     * @param runnable
     */
    void execute(Runnable runnable);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 获取初始线程数
     */
    int getInitSize();

    /**
     * 获取核心线程数
     */
    int getCoreSize();

    /**
     * 获取最大线程数
     */
    int getMaxSize();

    /**
     * 是否关闭
     */
    boolean isShutdown();

    int getActiveCount();

    int getQueueSize();
}
