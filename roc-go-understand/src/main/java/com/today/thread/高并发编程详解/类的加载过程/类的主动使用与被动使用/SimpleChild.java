package com.today.thread.高并发编程详解.类的加载过程.类的主动使用与被动使用;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月06日 11:53*
 * log.info()
 */
public class SimpleChild extends Simple {

    static {
        System.out.println(" simpleChild will be initialized ");
    }

    public static int y = 20;

    public static void test(){
        System.out.println("simpleChild test ");
    }

}
