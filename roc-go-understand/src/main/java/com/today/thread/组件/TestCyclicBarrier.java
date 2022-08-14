package com.today.thread.组件;

import java.util.Random;
import java.util.concurrent.*;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月22日 22:04*
 * log.info()
 */
public class TestCyclicBarrier {
    /**
     * 让一组线程，到达某个屏障，然后被阻塞，一直到最后这一组里面的组内最后一个线程到达屏障就开放
     * 屏障开放，所有被阻塞的线程就继续运行
     *
     * 与CountdownLaunch区别，cyclicBarrier是由工作线程内部控制是否放开
     * countdownLaunch是countdownLaunch自身控制是否放开非工作线程内部控制
     *
     * 1、countdownlatch放行由第三者控制，CyclicBarrier放行由一组线程本身控制
     * 2、countdownlatch放行条件>=线程数，CyclicBarrier放行条件=线程数
     */
    CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Thread(() -> {
        System.out.println("屏障放开，开始执行");
    }
    ));

    public static void main(String[] args) throws InterruptedException {
        new TestCyclicBarrier().test();
    }

    /**
     * 模拟赛跑比赛
     * 指令枪一响，5个运动员立刻出发
     */
    public void test() throws InterruptedException {
        //设置keepAliveTime单位
        TimeUnit unit = TimeUnit.SECONDS;
        //设置队列大小
        BlockingQueue workQueue = new ArrayBlockingQueue<>(100);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0, unit, workQueue);
        executor.setThreadFactory(new ThreadComponent.CountdownLaunchTest.DefaultThreadFactory());
        for (int i = 1; i <= 5; i++) {
            System.out.println("运动员 " + i + "号准备");
            Thread thread = new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "进入等待");
                    cyclicBarrier.await();
                    int time = new Random().nextInt(10) + 1;
                    TimeUnit.SECONDS.sleep(time);
                    System.out.println(Thread.currentThread().getName() + "--" + time + "秒");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, i + "号运动员");
            executor.execute(thread);
        }
        executor.shutdown();
    }

}
