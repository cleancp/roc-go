package com.tuling.concurrency.course13_aqs_queue;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * description：
 * 线程饥饿导致的死锁
 *
 * @author：roc.zou 2022/9/29 4:39 下午
 */
@Slf4j
public class HungryLockLearn {

    static final List<String> FOODS = Arrays.asList("猪脚饭", "宫保鸡丁", "鱼香肉丝", "麻婆豆腐");

    static final Random RANDOM = new Random();

    static ExecutorService pool = Executors.newFixedThreadPool(2);

    //随机做菜
    public static String cooking() {
        return FOODS.get(RANDOM.nextInt(FOODS.size()));
    }

    public static void main(String[] args) {
        pool.execute(()->{
            //服务员开始点菜
            log.info("开始给顾客点菜");
            Future<String> food = pool.submit(() -> {
                log.info("开始做菜");
                return cooking();
            });

            //该服务员点完菜上菜
            try {
                log.info("上菜:{}", food.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        pool.execute(()->{
            //服务员开始点菜
            log.info("开始给顾客点菜");
            Future<String> food = pool.submit(() -> {
                log.info("开始做菜");
                return cooking();
            });

            //该服务员点完菜上菜
            try {
                log.info("上菜:{}", food.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

}
