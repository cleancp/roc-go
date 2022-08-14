package com.today.java8._02funcinterface;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年06月13日 20:26*
 * log.info()
 */
@FunctionalInterface
public interface _01FunctionInterface {

    String say(String msg);

    default void eat(){
        System.out.println("函数式编程：default方法");
    }
}
