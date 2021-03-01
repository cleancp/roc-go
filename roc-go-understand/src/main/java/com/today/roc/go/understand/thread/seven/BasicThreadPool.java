package com.today.roc.go.understand.thread.seven;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月01日 23:38*
 * log.info()
 */
public class BasicThreadPool extends Thread implements ThreadPool {

    /**
     * 初始线程
     */
    private int initSize;
    /**
     * 核心线程
     */
    private int coreSize;
    /**
     * 最大线程
     */
    private int maxSize;

    /**
     * 空闲线程存活时间
     */
    private int keepAliveTime;

    /**
     * 存活时间单位
     */
    private TimeUnit timeUnit;

    /**
     * 当前活跃的线程数量
     */
    private int activeCount;

    /**
     * 是否关闭
     */
    private volatile boolean isShutdown = false;

    private ThreadFactory threadFactory;

    private LinkedRunnableQueue runnableQueue;

    public static final ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();
    public static final DenyPolicy    DEFAULT_DENY_POLICY    = new DenyPolicy.DiscardDenyPolicy();

    /**
     * 工作线程
     */
    private Queue<ThreadTask> threadTaskQueue = new ArrayDeque<>();

    public BasicThreadPool(int initSize, int coreSize, int maxSize, int queueSize) {
        this(initSize, coreSize, maxSize, DEFAULT_THREAD_FACTORY, queueSize, 10, TimeUnit.SECONDS, DEFAULT_DENY_POLICY);
    }

    public BasicThreadPool(int initSize, int coreSize, int maxSize, ThreadFactory threadFactory,
                           int queueSize, int keepAliveTime, TimeUnit timeUnit, DenyPolicy denyPolicy) {
        this.initSize = initSize;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.threadFactory = threadFactory;
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.init();
    }

    @Override
    public void run() {
        //继承自Thread 用于维护线程数量，比如扩容、回收等工作
        while (!isShutdown && !isInterrupted()) {
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                shutdown();
                break;
            }

            synchronized (this) {
                if (isShutdown) {
                    break;
                }

                //当队列有任务处理，且activeCount < coreSize 继续扩容
                if (runnableQueue.size() > 0 && activeCount < coreSize) {
                    for (int i = activeCount; i < coreSize; i++) {
                        createThread();
                    }
                    //continue是为了防止 线程扩容一下子到maxSize
                    continue;
                }
                //当前队列有任务，且活跃线程数大于核心线程数 小于最大线程数
                if (runnableQueue.size() > 0 && activeCount < maxSize) {
                    for (int i = coreSize; i < activeCount; i++) {
                        createThread();
                    }
                }
                //如果队列中没有任务 需要回收线程
                if (runnableQueue.size() == 0 && activeCount > coreSize) {
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }
    }

    /**
     * 线程池初始化线程
     */
    private void init() {
        this.start();
        for (int i = 0; i < initSize; i++) {
            createThread();
        }
    }

    private void createThread() {
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadTaskQueue.offer(threadTask);
        activeCount++;
        //其实就是internalTask
        thread.start();
    }

    private void removeThread() {
        //取出一个工作的任务线程
        ThreadTask threadTask = threadTaskQueue.remove();
        //标记为不运行，如果线程拿到任务还是会继续执行
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutdown) {
                return;
            }
            threadTaskQueue.forEach(
                    v -> {
                        v.internalTask.stop();
                        //
                        v.thread.interrupt();
                    }
            );
            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        return this.initSize;
    }

    @Override
    public int getCoreSize() {
        return this.coreSize;
    }

    @Override
    public int getMaxSize() {
        return this.maxSize;
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    @Override
    public int getActiveCount() {
        synchronized (this) {
            return this.activeCount;
        }
    }

    @Override
    public int getQueueSize(){
        return runnableQueue.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("activeCount："+ getActiveCount()+"\n");
        sb.append("queueSize："+getQueueSize()+"\n");
        sb.append("threadTaskQueueSize："+threadTaskQueue.size()+"\n");
        return sb.toString();
    }
}
