package com.today.roc.go.understand.thread.six;

import java.util.concurrent.TimeUnit;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年02月28日 20:52*
 * log.info()
 */
public class UncaughtExceptionMain {

    public static void main(String[] args) {
        //uncaughtException();
        testNoUncaught();
    }

    /**
     * 使用钩子 处理异常
     */
    private static void uncaughtException() {
        Thread.setDefaultUncaughtExceptionHandler((t,e)->{
            System.out.println(t);
            e.printStackTrace();
        });
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1/0);
        },"thread_uncaught").start();
    }

    private static void testNoUncaught(){
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println(mainGroup);
        System.out.println(mainGroup.getParent());
        System.out.println(mainGroup.getParent().getParent());
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1/0);
        },"uncaught_thread").start();
    }

}
