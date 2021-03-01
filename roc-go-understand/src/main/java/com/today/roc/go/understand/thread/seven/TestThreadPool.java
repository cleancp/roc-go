package com.today.roc.go.understand.thread.seven;

import java.util.concurrent.TimeUnit;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月02日 01:22*
 * log.info()
 */
public class TestThreadPool {

    public static void main(String[] args) throws InterruptedException {

        ThreadPool pool = new BasicThreadPool(2,4,6,1000);
        for (int i = 0; i < 20; i++) {
            pool.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread()+ " is running and done .");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        while (true){
            System.out.println(pool);
            TimeUnit.SECONDS.sleep(5);
        }

    }

}
