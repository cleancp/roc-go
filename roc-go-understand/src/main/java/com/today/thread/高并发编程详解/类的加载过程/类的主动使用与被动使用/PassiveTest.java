package com.today.thread.高并发编程详解.类的加载过程.类的主动使用与被动使用;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月06日 12:06*
 * log.info()
 */
public class PassiveTest {

    public static void main(String[] args) {
        //不加载 固定常量值的静态常量不会导致类初始化
        System.out.println(PassiveSimple.a);
        //加载  对象常量值的静态常量会导致类初始化
        System.out.println(PassiveSimple.b);
    }

}
