package com.today.roc.go.understand.thread.seven;

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
