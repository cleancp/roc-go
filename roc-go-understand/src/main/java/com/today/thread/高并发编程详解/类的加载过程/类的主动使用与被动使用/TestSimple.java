package com.today.thread.高并发编程详解.类的加载过程.类的主动使用与被动使用;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月06日 11:49*
 * log.info()
 */
public class TestSimple {

    public static void main(String[] args) throws ClassNotFoundException {
        //初始化
        //System.out.println("访问静态变量："+Simple.x);
        Simple.x = 1000;//此时会初始化类，但是输出值为0还没有赋值
        System.out.println(Simple.x);//输出1000
        //Simple.test();
        //System.out.println(SimpleChild.y);
        //只会导致父类初始化，子类不会初始化
        //System.out.println(SimpleChild.x);

        //反射会导致初始化
        //Class.forName("com.today.roc.go.understand.thread.高并发编程详解.类的加载过程.类的主动使用与被动使用.SimpleChild");
    }

}
