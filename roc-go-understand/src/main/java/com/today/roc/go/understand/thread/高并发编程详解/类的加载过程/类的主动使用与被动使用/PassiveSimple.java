package com.today.roc.go.understand.thread.高并发编程详解.类的加载过程.类的主动使用与被动使用;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月06日 12:07*
 * log.info()
 */
public class PassiveSimple {

    static {
        System.out.println("PassiveSimple will be initialized");
    }

    public static final String a = "123";

    public static final String b = "123".toString();
}
