package com.today.thread.高并发编程详解.类的加载过程.类的主动使用与被动使用;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月06日 11:46*
 * log.info()
 */
public class Simple {

    /**
     *  访问静态变量 x
     */
     static {
        System.out.println(" I will be initialized ：" + Simple.x);
    }
    public static int x = 10;

    /**
     *  访问静态方法
     */
    public static void test(){
        System.out.println("访问静态方法");
    }


}
