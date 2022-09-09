package com.tuling.concurrency.course10_aqs_other;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * description：
 *
 * @author：roc.zou 2022/9/7 4:12 下午
 */
@Slf4j
public class CountDownLatchLearn {


    static int sum = 0;

    public static void main(String[] args) {
        // commandGun();
        // statisticalSummary();
    }

    /**
    * @description: 多个线程统计，最终由一个线程汇总
    * @param: []
    * @return: void
    * @author: roc.zou
    * @date: 2022/9/7 6:00 下午
    */
    private static void statisticalSummary() throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                log.debug("{} begin statistics", Thread.currentThread().getName());
                try {
                    int sleepSec = ThreadLocalRandom.current().nextInt(3000);
                    log.debug("{} 值 {}" , Thread.currentThread().getName(), sleepSec);
                    Thread.sleep(sleepSec);
                    sum += sleepSec;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    log.debug("{} finish statistics", Thread.currentThread().getName());
                    cdl.countDown();
                }
            }).start();
        }
        cdl.await();
        log.debug("{} 统计汇总: {}", Thread.currentThread().getName(), sum);
    }


    /**
    * @description: 指令枪
    * @param: []
    * @return: void
    * @author: roc.zou
    * @date: 2022/9/7 5:53 下午
    */
    private static void commandGun() throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    log.debug("{} wait ", Thread.currentThread().getName());
                    cdl.await();
                    log.debug("{} wakeup  ", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }, "Thread"+i).start();
        }
        Thread.sleep(1000);
        log.debug("{} countdown", Thread.currentThread().getName());
        cdl.countDown();
    }

}
