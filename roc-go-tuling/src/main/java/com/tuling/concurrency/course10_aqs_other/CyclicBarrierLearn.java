package com.tuling.concurrency.course10_aqs_other;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description：
 *
 * @author：roc.zou 2022/9/7 6:33 下午
 */
@Slf4j
public class CyclicBarrierLearn {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(3);

    private static Map<String,Integer> MAP = new HashMap<>();

    public static void main(String[] args) {
        //waitThread();
        //statisticsAvgScore();
        cyclicCompete();
    }

    /**
    * @description: 不停比赛
    * @param: 
    * @return: 
    * @author: roc.zou
    * @date: 2022/9/8 5:28 下午
    */
    private static void cyclicCompete(){
        AtomicInteger threadNameAtomic = new AtomicInteger();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 20, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                (r)->new Thread(r, threadNameAtomic.addAndGet(1)+"号"),
                new ThreadPoolExecutor.AbortPolicy());

    }


    /**
    * @description: 统计平均分
    * @param: []
    * @return: void
    * @author: roc.zou
    * @date: 2022/9/8 5:23 下午
    */
    private static void statisticsAvgScore(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, ()->{
            // 统计平均数
            Collection<Integer> values = MAP.values();
            Integer avg = 0;
            for (Integer value : values) {
                avg+=value;
            }
            log.debug(" 统计平均值 {} " , avg/3);
        });
        for (int i = 0; i < 3; i++) {
            threadPool.submit(()->{
                try {
                    int score = (int) (Math.random() * 40 + 60);
                    MAP.put(Thread.currentThread().getName(), score);
                    log.debug("{} 分数 {}", Thread.currentThread().getName() , score);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    
    /**
    * @description: 限定获取资源的线程
    * @param: []
    * @return: void
    * @author: roc.zou
    * @date: 2022/9/8 5:24 下午
    */
    private static void waitThread() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    log.debug("{} wait before  wait num {}  parties {}", Thread.currentThread().getName(), cyclicBarrier.getNumberWaiting(), cyclicBarrier.getParties());
                    cyclicBarrier.await();
                    log.debug("{} begin exec business ", Thread.currentThread().getName());
                    Thread.sleep(3000);
                    log.debug("{} finish exec business ", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"Thread "+i).start();
        }
    }
}
