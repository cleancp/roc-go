package com.today.roc.go.understand.designPatterns.单例模式;

import java.io.Serializable;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月09日 01:00*
 * log.info()
 */
public enum SingletonEnum implements Serializable {

    /**
     * 优点：枚举不允许继承，线程安全，只能被实例化一次
     * 缺点：枚举不能够懒加载
     *  结论：推荐
     */

    INSTANCE;
    //实例变量
    private byte[] data = new byte[1024];

    SingletonEnum(){
        System.out.println("INSTANCE will be initialized immediately");
    }

    public void doSomething(){
        System.out.println("做点啥事情");
    }

    public static void method(){
        //调用该方法则会主动使用SingletonEnum，INSTANCE将会被实例化
    }
    public static  SingletonEnum getInstance(){
        return INSTANCE;
    }



}
