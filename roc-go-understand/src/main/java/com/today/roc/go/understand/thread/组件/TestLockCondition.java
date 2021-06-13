package com.today.roc.go.understand.thread.组件;

import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月23日 15:43*
 * log.info()
 */
public class TestLockCondition {

    public static void main(String[] args) throws InterruptedException {
        Express express = new Express(100, "sh");
        new Thread(() -> {
            try {
                express.waitKmChange();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                express.waitSiteChange();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(() -> {
            express.changeSite("sz");
        }).start();
        new Thread(() -> {
            express.changeKM(88);
        }).start();
    }

    @Data
    static class Express {
        private int    km; //公里数
        private String site;//地点

        public Express(int km, String site) {
            this.km = km;
            this.site = site;
        }

        private Lock      lock    = new ReentrantLock();
        private Condition kmCon   = lock.newCondition();
        private Condition siteCon = lock.newCondition();

        //公里数变化
        public void changeKM(int km) {
            try {
                lock.lock();
                this.km = km;
                System.out.println("变化公里数：" + km);
                kmCon.signal();
            } finally {
                lock.unlock();
            }
        }

        //地址变化
        public void changeSite(String address) {
            try {
                lock.lock();
                this.site = address;
                System.out.println("变化地址：" + address);
                siteCon.signal();
            } finally {
                lock.unlock();
            }
        }

        //地址变化业务变化
        public void waitSiteChange() throws InterruptedException {
            try {
                lock.lock();
                System.out.println("当前地址：" + this.site + " 更新时通知");
                if (this.site.equals("sh")) {
                    siteCon.await();
                    System.out.println("waitSiteChange：" + this.site);
                }
            } finally {
                lock.unlock();
            }
        }

        //公里变化业务变化
        public void waitKmChange() throws InterruptedException {
            try {
                lock.lock();
                System.out.println("当前公里数：" + this.km + " 更新时通知");
                if (this.km <= 100) {
                    kmCon.await();
                    System.out.println("waitKmChange：" + this.km);
                }
            } finally {
                lock.unlock();
            }
        }
    }

}
