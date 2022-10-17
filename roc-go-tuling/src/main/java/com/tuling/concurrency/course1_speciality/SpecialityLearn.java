package com.tuling.concurrency.course1_speciality;

import lombok.extern.slf4j.Slf4j;

/**
 * description：
 *
 * @author：roc.zou 2022/10/17 11:51 上午
 */
@Slf4j
public class SpecialityLearn {

    int count = 0;
    boolean flag = true;

    public static void main(String[] args) {
        SpecialityLearn learn = new SpecialityLearn();
        // 可见性
        // learn.visibilityLearn();
        // 原子性
        // learn.atomicityLearn();
        // 有序性 ， 指令重排 , DCL 双重校验锁
    }

    private void atomicityLearn() {
        Thread a = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
            log.info("A 执行完毕");
        }, "A");
        Thread b = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
            log.info("B 执行完毕");
        }, "B");
        a.start();
        b.start();
        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("count = "+count);
    }

    public void visibilityLearn(){
        // 一个线程能否看到另一个线程对共享变量的修改
        new Thread(()->{
            log.info(Thread.currentThread().getName() + "开始执行: count=" + count);
            while (flag){
                count++;
            }
            log.info(Thread.currentThread().getName() + "跳出循环: count=" + count);
        }, "A").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            flag = false;
            log.info("{} flag 置为 false", Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + " flag 置为 false");
        },"B").start();
    }

    public void waitTime(Integer waitMillis){
        long endTime = System.currentTimeMillis() + waitMillis;
        while (System.currentTimeMillis() < endTime){
        }
    }

}
