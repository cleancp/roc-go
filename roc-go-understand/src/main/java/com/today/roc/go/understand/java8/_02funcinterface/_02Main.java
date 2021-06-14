package com.today.roc.go.understand.java8._02funcinterface;

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
public class _02Main {

    public static void main(String[] args) {
        _01FunctionInterface i = new _01FunctionInterface() {
            @Override
            public String say(String msg) {
                return null;
            }
        };
        //匿名内部类的改进 增加default方法支持
        _01FunctionInterface b = (a) -> "" + a;
        //大括号可省略
//        _01FunctionInterface b1 = a -> "" + a;
        String hello = b.say("hello");
        b.eat();
    }
}
