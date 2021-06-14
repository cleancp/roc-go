package com.today.roc.go.understand.java8._01default;/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年06月13日 20:04*
 * log.info()
 */

public interface TestDefault {

    public static final String _static = "1";
    String _static_2 = "2";

    void say();

    default void eat(String food){
        System.out.println("接口："+food);
    }

}
