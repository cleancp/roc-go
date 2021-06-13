package com.today.roc.go.understand.thread.synchronous;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

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
 * @createTime 2020年09月25日 09:32*
 * log.info()
 */
public class SynchronizedMain {
    private static Object staticObj1 = new Object();
    private static Object staticObj2 = new Object();
    private        Object obj1       = new Object();
    private        Object obj2       = new Object();

    public synchronized void methodTest() {
        sout(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public synchronized void methodTestTwo() {
        sout(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public synchronized static void statisMethodTest() {
        soutStatic(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public synchronized static void statisMethodTestTwo() {
        soutStatic(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void lockStaticObj1() {
        synchronized (staticObj1) {
            System.out.println(Thread.currentThread().getName() + "开始准备睡眠 lockStaticObj1");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "睡眠完成 lockStaticObj1");
        }
    }

    public void lockStaticObj2() {
        synchronized (staticObj2) {
            System.out.println(Thread.currentThread().getName() + "开始准备睡眠 lockStaticObj2");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "睡眠完成 lockStaticObj2");
        }
    }

    public void otherObjTest() {
        System.out.println("otherObjTest begin");
        synchronized (obj1) {
            sout(Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    public void otherObjTwoTest() {
        System.out.println("otherObjTwoTest begin");
        synchronized (obj2) {
            sout(Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    public void lockObj() {
        System.out.println("lockObj begin");
        synchronized (this) {
            sout(Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    public void lockObjTwo() {
        System.out.println("lockObjTwo begin");
        synchronized (this) {
            sout(Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    public static void main(String[] args) {
        SynchronizedMain main = new SynchronizedMain();
        //lockStatic();
        //lockThisObj(main);
        //lockMethod(main);
        //lockOtherObj(main);
        SynchronizedMain main1 = new SynchronizedMain();
        lockStaticObj(main, main1);
    }


    private static void lockOtherObj(SynchronizedMain main) {
        new Thread() {
            @Override
            public void run() {
                main.otherObjTest();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                main.otherObjTwoTest();
            }
        }.start();
    }

    /**
     * 锁住其它对象
     */
    private static void lockStaticObj(SynchronizedMain main, SynchronizedMain main1) {
        new Thread() {
            @Override
            public void run() {
                main.lockStaticObj1();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                main1.lockStaticObj2();
            }
        }.start();
    }

    /**
     * 锁的是类对象，所有调用静态方法都会被锁住
     */
    private static void lockStatic(Method method) {
        new Thread() {
            @Override
            public void run() {
                SynchronizedMain.statisMethodTest();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                SynchronizedMain.statisMethodTestTwo();
            }
        }.start();
    }

    /**
     * 锁的还是调用该方法的对象，也就是this ， 如果是不同对象，锁不生效
     *
     * @param main
     */
    private static void lockMethod(SynchronizedMain main) {
        new Thread() {
            @Override
            public void run() {
                main.methodTest();
                //new SynchronizedMain().methodTest();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                main.methodTestTwo();
                //new SynchronizedMain().methodTestTwo(); 不受上一个锁影响
            }
        }.start();
    }

    /**
     * 锁的还是调用该方法的对象，也就是this ， 如果是不同对象，锁不生效
     *
     * @param main
     */
    private static void lockThisObj(SynchronizedMain main) {
        new Thread() {
            @Override
            public void run() {
                //main.lockObj();
                new SynchronizedMain().lockObj();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                //main.lockObjTwo();
                new SynchronizedMain().lockObjTwo();
            }
        }.start();
    }

    public void sout(String method) {
        soutStatic(method);
    }

    public static void soutStatic(String method) {
        String name = Thread.currentThread().getName();
        System.out.println(name + "exec " + method);
        try {
            Thread.sleep(1000);
            System.out.println(name + "在" + method + " 睡眠 1s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
