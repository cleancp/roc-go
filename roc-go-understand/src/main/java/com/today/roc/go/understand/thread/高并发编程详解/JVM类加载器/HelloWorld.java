package com.today.roc.go.understand.thread.高并发编程详解.JVM类加载器;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月07日 11:05*
 * log.info()
 */
public class HelloWorld {

    static {
        System.out.println(" HelloWorld class static code block");
    }

    public String hello(){
        System.out.println(" HelloWorld hello method");
        return "hello";
    }

}
