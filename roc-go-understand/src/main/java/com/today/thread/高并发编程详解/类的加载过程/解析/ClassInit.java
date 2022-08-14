package com.today.thread.高并发编程详解.类的加载过程.解析;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月06日 21:33*
 * log.info()
 */
public class ClassInit {
    static {
        //System.out.println(x);//编译报错
        //x  = 1000;
        try {
            System.out.println(" The ClassInit static code block will be invoke  ");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("10秒之后输出");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static int x = 10;

    static class Parent{
        static int value = 10;
        static {
            value = 20;
        }
    }

    static class Child extends Parent{
        static  int i = value;
    }

    public static void main(String[] args) {
        //System.out.println(Child.i);//会输出20 ， 因为虚拟机会保证父类的init方法最优先执行
        IntStream.range(0,5).forEach(i->new Thread(ClassInit::new));// The ClassInit static code block will be invoke 10秒之后输出
    }
}
