package com.today.roc.go.understand.thread.组件;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月04日 23:53*
 * log.info()
 */
public class ThreadComponent {

    public static void main(String[] args) throws InterruptedException {
        //AbstractQueuedSynchronizer
        ThreadComponent.CountdownLaunchTest countdownLaunchTest = new CountdownLaunchTest();
        countdownLaunchTest.test();
    }

    static class CountdownLaunchTest {
        /**
         * 线程池 线程工厂
         */
        static class DefaultThreadFactory implements ThreadFactory {
            private static final AtomicInteger poolNumber   = new AtomicInteger(1);
            private final        AtomicInteger threadNumber = new AtomicInteger(1);
            private final        ThreadGroup   group;
            private final        String        namePrefix;

            DefaultThreadFactory() {
                SecurityManager s = System.getSecurityManager();
                group = (s != null) ? s.getThreadGroup() :
                        Thread.currentThread().getThreadGroup();
                namePrefix = "pool-" +
                        poolNumber.getAndIncrement() +
                        "-thread-运动员-";
            }

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement());
                if (t.isDaemon())
                    t.setDaemon(false);
                if (t.getPriority() != Thread.NORM_PRIORITY)
                    t.setPriority(Thread.NORM_PRIORITY);
                return t;
            }
        }

        /**
         * 模拟赛跑比赛
         * 指令枪一响，5个运动员立刻出发
         */
        public void test() throws InterruptedException {
            ReentrantLock lock = new ReentrantLock();
            Condition condition = lock.newCondition();
            condition.await();
            condition.signal();
            condition.signalAll();

            CountDownLatch start = new CountDownLatch(1);
            CountDownLatch people = new CountDownLatch(5);
            //设置keepAliveTime单位
            TimeUnit unit = TimeUnit.SECONDS;
            //设置队列大小
            BlockingQueue workQueue = new ArrayBlockingQueue<>(100);
            ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0, unit, workQueue);
            executor.setThreadFactory(new DefaultThreadFactory());
            for (int i = 1; i <= 5; i++) {
                System.out.println("运动员 " + i + "号准备");
                Thread thread = new Thread(() -> {
                    try {
                        start.await();
                        int time = new Random().nextInt(10) + 1;
                        TimeUnit.SECONDS.sleep(time);
                        System.out.println(Thread.currentThread().getName() + "--" + time + "秒");
                        people.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, i + "号运动员");
                executor.execute(thread);
//                thread.start();
            }
            System.out.println("所有运动员都已经准备好");
            start.countDown();
            System.out.println("发起指令枪-所有运动员开跑");
            people.await();
            System.out.println("所有运动员都到终点");
            executor.shutdown();
        }


    }

/**
 protected final boolean tryAcquire(int acquires) {
 final Thread current = Thread.currentThread();
 int c = getState();
 if (c == 0) {
 if (!hasQueuedPredecessors() && compareAndSetState(0, acquires)) {
 setExclusiveOwnerThread(current);
 return true;
 }
 } else
 }
 */

}
