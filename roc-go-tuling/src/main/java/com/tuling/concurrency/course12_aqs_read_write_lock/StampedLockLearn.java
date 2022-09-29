package com.tuling.concurrency.course12_aqs_read_write_lock;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.StampedLock;

/**
 * description：
 *
 * @author：roc.zou 2022/9/20 8:25 下午
 */
@Slf4j
public class StampedLockLearn {

    public static void main(String[] args) throws InterruptedException {
        Point point = new Point();
        new Thread(()->{
            point.move(50,80);
        }, "A").start();
        Thread.sleep(500);
        new Thread(()->{
            try {
                point.distanceFromOrigin();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
        Thread.sleep(500);
        new Thread(()->{
            point.move(50,80);
        }, "C").start();
    }
}

@Slf4j
@Data
class Point{
    final StampedLock stampedLock = new StampedLock();
    private int x;
    private int y;

    public void move(int instanceX , int instanceY){
        long stamp = stampedLock.writeLock();
        log.info("{} get write lock ", Thread.currentThread().getName());
        try{
            x+= instanceX;
            y+= instanceY;
            log.info("{} release write lock ", Thread.currentThread().getName());
        }finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public Integer distanceFromOrigin() throws InterruptedException {
        long l = stampedLock.tryOptimisticRead();
        int currentX = this.x;
        log.info("{} tryOptimisticRead x :{} y:{} currentX:{} ", Thread.currentThread().getName(), x, y, currentX);
        Thread.sleep(2000);
        int currentY = this.y;
        log.info("{} tryOptimisticRead sleep x:{} y:{} currentX:{} currentY:{}", Thread.currentThread().getName(), x, y, currentX, currentY);
        if (!stampedLock.validate(l)){
            long stamp = stampedLock.readLock();
            try{
                currentX = this.x;
                currentY = this.y;
                log.info("{} validate readLock x:{} y:{} currentX:{} currentY:{}", Thread.currentThread().getName(), x, y, currentX, currentY);
            }finally {
                stampedLock.unlock(stamp);
            }
        }
        return x*x + y*y;
    }
}

