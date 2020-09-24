package com.today.roc.go.understand.thread;

import java.util.Random;
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
 * @createTime 2020年09月24日 09:34*
 * log.info()
 */
public class DeadLock {

    public static Object        o1    = new Object();
    public static Object        o2    = new Object();
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    static class LockMain extends Thread implements Runnable {

        boolean flag;

        public LockMain(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("线程" + this.getName() + "开始执行");
                synchronized (o1) {
                    System.out.println("线程" + this.getName() + "锁住了o1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o2) {
                        System.out.println("线程" + this.getName() + "锁住了o2");
                    }
                }
            } else {
                System.out.println("线程" + this.getName() + "开始执行");
                synchronized (o2) {
                    System.out.println("线程" + Thread.currentThread().getName() + "锁住了o2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o1) {
                        System.out.println("线程" + Thread.currentThread().getName() + "锁住了o1");

                    }
                }
            }
        }
    }

    static class ReentrantLockMain extends Thread implements Runnable {

        boolean flag;

        public ReentrantLockMain(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("线程" + this.getName() + "开始执行");
                try {
                    if (lock1.tryLock()) {
                        try {
                            System.out.println("线程" + this.getName() + "锁住了lock1");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            if (!lock2.tryLock()) {
                                System.out.println("线程" + Thread.currentThread().getName() + "锁住lock2失败");
                            } else {
                                try {
                                    System.out.println("线程" + this.getName() + "锁住了lock2");
                                } finally {
                                    lock2.unlock();
                                }
                            }
                        } finally {
                            lock1.unlock();
                            System.out.println("线程" + Thread.currentThread().getName() + "解锁lock1");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("线程" + this.getName() + "开始执行");
                try {
                    if (lock2.tryLock()) {
                        try {
                            System.out.println("线程" + Thread.currentThread().getName() + "锁住了lock2");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (!lock1.tryLock()) {
                                System.out.println("线程" + Thread.currentThread().getName() + "锁住lock1失败");
                            } else {
                                try {
                                    System.out.println("线程" + Thread.currentThread().getName() + "锁住了lock1");
                                } finally {
                                    lock1.unlock();
                                }
                            }
                        } finally {
                            lock2.unlock();
                            System.out.println("线程" + Thread.currentThread().getName() + "解锁lock2");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class LiveLockMain extends Thread implements Runnable {

        boolean flag;

        public LiveLockMain(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("线程" + this.getName() + "开始执行");
                while (true) {
                    try {
                        if (lock1.tryLock()) {
                            Thread.sleep(new Random().nextInt(1000));
                            try {
                                System.out.println("线程" + this.getName() + "锁住了lock1");
                                if (!lock2.tryLock()) {
                                    System.out.println("线程" + Thread.currentThread().getName() + "锁住lock2失败");
                                } else {
                                    try {
                                        System.out.println("线程" + this.getName() + "锁住了lock2");
                                        break;
                                    } finally {
                                        lock2.unlock();
                                        System.out.println("线程" + Thread.currentThread().getName() + "解锁lock2");
                                    }
                                }
                            } finally {
                                lock1.unlock();
                                System.out.println("线程" + Thread.currentThread().getName() + "解锁lock1");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("-------------------------------------");

                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("线程" + this.getName() + "开始执行");
                while (true) {
                    try {
                        if (lock2.tryLock()) {
                            try {
                                Thread.sleep(new Random().nextInt(1000));
                                System.out.println("线程" + Thread.currentThread().getName() + "锁住了lock2");
                                if (!lock1.tryLock()) {
                                    System.out.println("线程" + Thread.currentThread().getName() + "锁住lock1失败");
                                } else {
                                    try {
                                        System.out.println("线程" + Thread.currentThread().getName() + "锁住了lock1");
                                        break;
                                    } finally {
                                        lock1.unlock();
                                        System.out.println("线程" + Thread.currentThread().getName() + "解锁lock1");
                                    }
                                }
                            } finally {
                                lock2.unlock();
                                System.out.println("线程" + Thread.currentThread().getName() + "解锁lock2");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("-------------------------------------");

                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        /**死锁问题*/
//        LockMain lockMain1 = new LockMain(true);
//        LockMain lockMain2 = new LockMain(false);
//        lockMain1.start();
//        lockMain2.start();
        /**避免死锁-顺序加锁*/
//        LockMain lockMain11 = new LockMain(true);
//        LockMain lockMain12 = new LockMain(true);
//        lockMain11.start();
//        lockMain12.start();
        /**避免死锁-避免嵌套封锁*/
        /**避免死锁-设置加锁时长*/
        /**避免死锁-使用ReentrantLock.tryLock()*/
//            ReentrantLockMain r1 = new ReentrantLockMain(true);
//            ReentrantLockMain r2 = new ReentrantLockMain(false);
//            r1.start();
//            r2.start();
        /**活锁：加不上就释放已获得的资源再重试，比较耗费资源 所以加了随机睡眠时间*/
        LiveLockMain l1 = new LiveLockMain(true);
        LiveLockMain l2 = new LiveLockMain(false);
        l1.start();
        l2.start();
    }
}
