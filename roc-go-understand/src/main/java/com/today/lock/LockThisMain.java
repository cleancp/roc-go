package com.today.lock;

import com.today.utils.date.DateUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

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
 * @createTime 2020年10月28日 09:55*
 * log.info()
 */
public class LockThisMain {

    public String one = "one";
    public String two = "two";
    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockThisMain main = new LockThisMain();
        //synchronized锁
        //lockThis(main);
        //ReentrantLock锁
        //reentrantLockThis(main);
        //可重入锁：一段锁住代码内可以继续进行加锁操作
        multiLock(main);
    }

    public void multiLockOne() {
        try {
            System.out.println(DateUtil.getCurrentDateStr() + " 进入multiLockOne 方法 ");
            synchronized (one) {
                System.out.println(DateUtil.getCurrentDateStr() + " 锁住 one ");
                TimeUnit.SECONDS.sleep(1);
                synchronized (this) {
                    System.out.println(DateUtil.getCurrentDateStr() + " one 锁住 this");
                    TimeUnit.SECONDS.sleep(5);
                }
                System.out.println(DateUtil.getCurrentDateStr() + " one 解锁 this");
            }
            System.out.println(DateUtil.getCurrentDateStr() + " one 解锁 one");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void multiLockTwo() {
        try {
            System.out.println(DateUtil.getCurrentDateStr() + " 进入multiLockTwo 方法 ");
            synchronized (two) {
                System.out.println(DateUtil.getCurrentDateStr() + " 锁住 two ");
                TimeUnit.SECONDS.sleep(1);
                synchronized (this) {
                    System.out.println(DateUtil.getCurrentDateStr() + " two 锁住 this");
                    TimeUnit.SECONDS.sleep(2);
                }
                System.out.println(DateUtil.getCurrentDateStr() + " two 解锁 this");
            }
            System.out.println(DateUtil.getCurrentDateStr() + " two 解锁 two");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reentrantLockOne() {
        System.out.println(DateUtil.getCurrentDateStr() + " 进入reetrantLockOne 方法 ");
        try {
            while (true) {
                boolean lockOne = lock.tryLock();
                if (lockOne) {
                    System.out.println(DateUtil.getCurrentDateStr() + " 进入reetrantLockOne 同步块 ");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println(DateUtil.getCurrentDateStr() + " reetrantLockOne休眠完成 ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                } else {
                    System.out.println(" 未进入reetrantLockOne同步块 ");
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void reentrantLockTwo() {
        System.out.println(DateUtil.getCurrentDateStr() + " 进入reetrantLockTwo 方法 ");
        try {
            while (true) {
                boolean lockTwo = lock.tryLock();
                if (lockTwo) {
                    System.out.println(DateUtil.getCurrentDateStr() + " 进入reetrantLockTwo 同步块 ");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println(DateUtil.getCurrentDateStr() + " reetrantLockTwo休眠完成 ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                } else {
                    System.out.println(" 未进入reetrantLockTwo同步块 ");
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void lockOne() {
        System.out.println(DateUtil.getCurrentDateStr() + "进入lockOne  方法");
        synchronized (this) {
            System.out.println(DateUtil.getCurrentDateStr() + "进入lockOne  synchronized块");
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(DateUtil.getCurrentDateStr() + "lockOne休眠完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void lockTwo() {
        System.out.println(DateUtil.getCurrentDateStr() + "进入lockTwo  方法");
        synchronized (this) {
            System.out.println(DateUtil.getCurrentDateStr() + "进入lockTwo  synchronized块");
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(DateUtil.getCurrentDateStr() + "lockTwo休眠完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void reentrantLockThis(LockThisMain main) {
        new Thread() {
            @Override
            public void run() {
                main.reentrantLockOne();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                main.reentrantLockTwo();
            }
        }.start();
    }

    private static void lockThis(LockThisMain main) {
        new Thread() {
            @Override
            public void run() {
                main.lockOne();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                main.lockTwo();
            }
        }.start();
    }

    private static void multiLock(LockThisMain main) {
        new Thread() {
            @Override
            public void run() {
                main.multiLockOne();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                main.multiLockTwo();
            }
        }.start();
    }

}

