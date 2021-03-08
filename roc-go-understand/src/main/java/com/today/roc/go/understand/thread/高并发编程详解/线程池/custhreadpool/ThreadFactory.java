package com.today.roc.go.understand.thread.高并发编程详解.线程池.custhreadpool;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月01日 23:33*
 * log.info()
 */
@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);

}
