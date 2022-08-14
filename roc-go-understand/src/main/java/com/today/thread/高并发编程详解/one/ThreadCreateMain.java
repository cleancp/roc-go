package com.today.thread.高并发编程详解.one;

import java.util.concurrent.*;

/**
 * Software License Declaration.
 * <p>
 * zhilingsd.com, Co,. Ltd.
 * Copyright © 2016 All Rights Reserved.
 * <p>
 * Copyright Notice
 * This documents is provided to zhilingsd contracting agent or authorized programmer only.
 * This source code is written and edited by zhilingsd Co,.Ltd Inc specially for financial
 * business contracting agent or authorized cooperative company, in order to help them to
 * install, programme or central control in certain project by themselves independently.
 * <p>
 * Disclaimer
 * If this source code is needed by the one neither contracting agent nor authorized programmer
 * during the use of the code, should contact to zhilingsd Co,. Ltd Inc, and get the confirmation
 * and agreement of three departments managers  - Research Department, Marketing Department and
 * Production Department.Otherwise zhilingsd will charge the fee according to the programme itself.
 * <p>
 * Any one,including contracting agent and authorized programmer,cannot share this code to
 * the third party without the agreement of zhilingsd. If Any problem cannot be solved in the
 * procedure of programming should be feedback to zhilingsd Co,. Ltd Inc in time, Thank you!
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年09月25日 11:38*
 * log.info()
 */
public class ThreadCreateMain {

    /**
     * 当前线程使用this获取
     */
    static class ThreadCreate extends Thread {
        @Override
        public void run() {
            System.out.println(this.getClass().getSimpleName() + "do something");
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(this.getClass().getSimpleName() + "do something");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 当前线程使用Thread.currentThread()获取
     */
    static class RunnableCreate implements Runnable {

        @Override
        public void run() {
            System.out.println(this.getClass().getSimpleName() + "do something");
        }
    }

    /**
     * 当前线程使用Thread.currentThread()获取
     * 有返回值 可以抛出异常
     */
    static class CallableCreate implements Callable {

        @Override
        public Object call() throws Exception {
            System.out.println(this.getClass().getSimpleName() + "do something");
            throw new RuntimeException();
        }

    }

    public static void main(String[] args) {
        testThreadCreate();
//        testRunnableCreate();
        testCallableCreate();
    }

    public static void testThreadCreate() {
        ThreadCreate threadCreate = new ThreadCreate();
        threadCreate.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadCreate.start();
    }

    public static void testRunnableCreate() {
        RunnableCreate runnableCreate = new RunnableCreate();
        new Thread(runnableCreate).start();
    }

    public static void testCallableCreate() {
        //CallableCreate callableCreate = new CallableCreate();
        FutureTask<Integer> task = new FutureTask((Callable<Integer>) () -> {
            Integer count = 0;
            for (int i = 0; i < 1000; i++) {
                count += i;
            }
            return count;
        });
        new Thread(task).start();
        Object obj = null;
        try {
            obj = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(obj);
    }

}
