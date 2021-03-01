package com.today.roc.go.understand.thread.seven;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月01日 23:43*
 * log.info()
 */
public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        //当前任务running 且未被打断
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (InterruptedException e) {
                running = false;
                break;
            }
        }
    }

    public void stop() {
        this.running = false;
    }
}
