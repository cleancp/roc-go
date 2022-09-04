package com.tuling.concurrency.course7_synchrinized;

import lombok.extern.slf4j.Slf4j;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2022年09月03日 22:30*
 * log.info()
 */
@Slf4j
public class WaitLearn {

    private static Object obj = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread("thread"+i){
                @Override
                public void run() {
                    testWaitAndSleep();
                }
            }.start();
        }
    }

    public static void testWaitAndSleep(){
        log.debug("{}执行testWaitAndSleep方法，before……", Thread.currentThread().getName());
        synchronized (obj){
            log.debug("{}执行testWaitAndSleep方法，ing……", Thread.currentThread().getName());
            try {
                //obj.wait(5000); // 会释放对象锁
                Thread.sleep(5000);// 不会释放对象锁，阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("{}执行testWaitAndSleep方法，wait after……", Thread.currentThread().getName());
        }
        log.debug("{}执行testWaitAndSleep方法，release lock……", Thread.currentThread().getName());
    }
}
