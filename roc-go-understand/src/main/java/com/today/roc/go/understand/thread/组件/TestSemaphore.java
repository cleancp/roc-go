package com.today.roc.go.understand.thread.组件;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月22日 22:31*
 * log.info()
 */
public class TestSemaphore {
    static final   Semaphore                   take    = new Semaphore(0);
    static final   Semaphore                   release = new Semaphore(10);
    private static ArrayBlockingQueue<Integer> sources = new ArrayBlockingQueue<Integer>(100);

    /**
     * 流量控制
     *
     * @param args
     */
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                    takeSource();
                    System.out.println(Thread.currentThread().getName() + "----资源数：" + sources.size() + "----- take数：" + take.availablePermits() + "----- release数：" + release.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "take线程").start();

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                    releaseSource(new Random().nextInt(1000));
                    System.out.println(Thread.currentThread().getName() + "----资源数：" + sources.size() + "----- take数：" + take.availablePermits() + "----- release数：" + release.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "release线程").start();
    }


    public static void releaseSource(Integer source) throws InterruptedException {
        if (Objects.nonNull(source)) {
            release.acquire();
            //System.out.println("release可用许可：" + release.availablePermits());
            sources.add(source);
            take.release();
        }
    }

    public static void takeSource() throws InterruptedException {
        Random random = new Random();
        if (!CollectionUtils.isEmpty(sources)) {
            take.acquire();
            Integer poll = sources.poll();
            System.out.println("poll结果：" + poll);
            //System.out.println("take可用许可：" + take.availablePermits());
            release.release();
        }
    }
}
