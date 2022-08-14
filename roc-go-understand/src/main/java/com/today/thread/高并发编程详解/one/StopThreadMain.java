package com.today.thread.高并发编程详解.one;

import lombok.Data;

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
 * @createTime 2020年10月10日 16:47*
 * log.info()
 */
public class StopThreadMain {

    static class StopThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 500000; i++) {
                System.out.println("i=" + (i + 1));
            }
        }
    }

    @Data
    static class FlagThread implements Runnable {
        public volatile boolean isExit = false;

        @Override
        public void run() {
            int i = 0;
            while (!isExit) {
                System.out.println(i++);
            }
        }
    }

    static class InterruptThread extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (!isInterrupted()) {
                System.out.println(i++);
            }
        }
    }

    public static void main(String[] args) {
        //testInterruptExit();
        //testFlagExit();
        //testInterruptThreadExit();
        testThreadStop();
    }

    private static void testThreadStop() {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            thread.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void testInterruptThreadExit() {
        Thread thread = new InterruptThread();
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(1);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void testInterruptExit() {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            Thread.sleep(1);
            thread.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testFlagExit() {
        try {
            FlagThread flagThread = new FlagThread();
            Thread thread1 = new Thread(flagThread);
            thread1.start();
            TimeUnit.SECONDS.sleep(1);
            flagThread.setExit(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
