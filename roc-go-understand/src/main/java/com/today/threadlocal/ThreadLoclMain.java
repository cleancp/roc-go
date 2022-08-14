package com.today.threadlocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

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
 * @createTime 2020年10月15日 20:47*
 * log.info()
 */
public class ThreadLoclMain {

    public static void main(String[] args) {
        ThreadLoclMain main = new ThreadLoclMain();
        main.learnThreadLocal();
        main.learnThreadLocalMap();
    }

    public void learnThreadLocalMap(){
        ThreadLocal threadLocal = new ThreadLocal();
        /**
         * ThreadLocalMap 是ThreadLocal的静态内部类，key就是this(ThreadLocal) value 是当前线程设置的值
         */
        threadLocal.set("1");
        threadLocal.set("2");
        Object o = threadLocal.get();
        threadLocal.remove();
    }

    private void learnThreadLocal() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        Random random = new Random();
        IntStream.range(0, 5).forEach(
                v -> {
                    new Thread(() -> {
                        threadLocal.set(v + " " + random.nextInt(10));
                        System.out.println("线程和存储值为："+threadLocal.get());
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
        );
    }
}
