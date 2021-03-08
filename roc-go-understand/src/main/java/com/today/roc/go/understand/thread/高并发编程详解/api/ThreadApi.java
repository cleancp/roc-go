package com.today.roc.go.understand.thread.高并发编程详解.api;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年02月27日 09:40*
 * log.info()
 */
public class ThreadApi {
    /**
     * 不会放弃monitor锁的所有权
     * @throws InterruptedException
     */
    public void sleep() throws InterruptedException {
        //指定线程休眠10时20分30秒
        Thread.sleep((10*3600+20*60+30)*1000);
        //TimeUnit方式
        TimeUnit.HOURS.sleep(10);
        TimeUnit.MINUTES.sleep(20);
        TimeUnit.SECONDS.sleep(30);
    }

    static class ThreadYield{
        public static void yield(){
            IntStream.range(0,2).mapToObj(ThreadYield::test).forEach(Thread::start);
        }

        public static Thread test(int index){
            return new Thread(()->{
                if (index == 0){
                    Thread.yield();
                }
                System.out.println(index);
            });
        }
    }

    static class ThreadPriority{

        public static void setPriority(){
            System.out.println(Thread.currentThread().getName() +" 优先级："+ Thread.currentThread().getPriority());
            Thread t =  new Thread(()->{
                System.out.println(Thread.currentThread().getName() +" 优先级："+ Thread.currentThread().getPriority());
                subThreadSetPriority();
            },"priority-1");
            t.setPriority(6);
            t.start();
        }

        public static void subThreadSetPriority(){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() +" 优先级："+ Thread.currentThread().getPriority());
            },"priority-2").start();
        }
    }

    public static void main(String[] args) {
        //testYield();
        testPriority();
    }

    private static void testPriority() {
        ThreadPriority.setPriority();
    }

    private static void testYield() {
        ThreadYield.yield();
        IntStream.range(0,50).forEach(
                v->{
                    System.out.println(v+" begin ");
                    ThreadYield.yield();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(v+ " end  ");
                }
        );
    }

}
