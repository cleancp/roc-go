package com.today.roc.go.understand.thread.高并发编程详解.four.booleanlock;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年02月28日 12:20*
 * log.info()
 */
public interface Lock {

    void lock() throws InterruptedException;

    void lock(long mills) throws TimeoutException, InterruptedException;

    void unlock();

    List<Thread> getBlockThreads();

}
