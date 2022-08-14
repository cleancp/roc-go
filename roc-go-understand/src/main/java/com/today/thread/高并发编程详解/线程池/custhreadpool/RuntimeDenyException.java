package com.today.thread.高并发编程详解.线程池.custhreadpool;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月01日 23:27*
 * log.info()
 */
public class RuntimeDenyException extends RuntimeException {
    public RuntimeDenyException(String message) {
        super(message);
    }
}
