package com.today.roc.go.understand.java8._02funcinterface;

import java.util.function.BiConsumer;

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
        //ownTest();
        testBiConsumer();
    }

    private static void ownTest() {
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

    public static void testBiConsumer(){
        BiConsumer<Integer,Integer> biConsumer = new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer integer, Integer integer2) {
                System.out.println("value1:"+integer);
                System.out.println("value2:"+integer2);
            }
        };
        BiConsumer<Integer,Integer> biConsumer2 = new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer integer, Integer integer2) {
                System.out.println("biConsumer2 value1:"+integer);
                System.out.println("biConsumer2 value2:"+integer2);
            }
        };
        //biConsumer.accept(1,2);
        biConsumer.andThen(biConsumer2).accept(1,2);
    }
}
