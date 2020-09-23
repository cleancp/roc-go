package com.today.roc.go.understand.hashmap;

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
 * @createTime 2020年09月23日 15:49*
 * log.info()
 */
public class ThreadYieldTest extends Thread{
    /**
     * Thread.yield()方法
     * 并发时，当前线程执行到该处，会让出时间片，然后重新处于就绪状态，
     * 可能下一次还是该线程抢到时间片，可能不是该线程
     */
    public static void main(String[] args) {
        ThreadYieldTest t1 = new ThreadYieldTest();
        ThreadYieldTest t2 = new ThreadYieldTest();
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 40; i++) {
            if (i==30){
                Thread.yield();
            }
            System.out.println(this.getName()+"---"+i);
        }
    }
}
