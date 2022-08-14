package com.today.thread.组件;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月23日 14:49*
 * log.info()
 */
public class TestReadWriteLock {

    List<String>                     sources       = Lists.newArrayList();
    ReentrantReadWriteLock           rw            = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock  getLock       = rw.readLock();
    ReentrantReadWriteLock.WriteLock setLock       = rw.writeLock();
    CyclicBarrier                    cyclicBarrier = new CyclicBarrier(50, new Runnable() {
        @Override
        public void run() {
            System.out.println("读写线程都准比好了，开始");
        }
    });

    public static void main(String[] args) {
        new TestReadWriteLock().test();
    }

    public void test() {
        for (int i = 0; i < 30; i++) {
            //30个线程写
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
//                    write();
                    syncWrite();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        for (int i = 0; i < 20; i++) {
            int anInt = new Random().nextInt(sources.size());
            //20个线程读
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                    syncRead(anInt);
//                    read(anInt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private void read(int anInt) {
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            getLock.lock();
            try {
                TimeUnit.MILLISECONDS.sleep(5);
                String remove = sources.get(anInt);
                System.out.println("获取" + anInt + "位数据：" + remove + " 花费时间：" + (System.currentTimeMillis() - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                getLock.unlock();
            }
        }
    }

    private void syncRead(int anInt) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            synchronized (sources) {
                TimeUnit.MILLISECONDS.sleep(5);

                String remove = sources.get(anInt);
                System.out.println("获取" + anInt + "位数据：" + remove + " 花费时间：" + (System.currentTimeMillis() - start));
            }
        }
    }

    private void write() {
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            setLock.lock();
            try {
                TimeUnit.MILLISECONDS.sleep(5);
                sources.add(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + " 写数据花费时间：" + (System.currentTimeMillis() - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                setLock.unlock();
            }
        }
    }

    private void syncWrite() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            synchronized (sources) {
                TimeUnit.MILLISECONDS.sleep(5);
                sources.add(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + " 写数据花费时间：" + (System.currentTimeMillis() - start));
            }
        }
    }

}
