package com.today.roc.go.understand.thread.高并发编程详解.five;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年02月28日 18:04*
 * log.info()
 */
public class ThreadGroupMain {

    public static void main(String[] args) throws InterruptedException {
        //create();
        //copyThreadFromThreadGroup();
        //setMaxPriority();
        //interrupt();
        destory();
    }

    /**
     * 创建
     */
    private static void create() {
        ThreadGroup threadGroup = currentThread().getThreadGroup();
        ThreadGroup group1 = new ThreadGroup("group1");
        System.out.println(threadGroup == group1.getParent());
        ThreadGroup group2 = new ThreadGroup(group1, "group2");
        System.out.println(group2.getParent() == group1);
    }

    /**
     * 复制
     */
    public static void copyThreadFromThreadGroup() {
        ThreadGroup mainGroup = currentThread().getThreadGroup();
        ThreadGroup subGroup1 = new ThreadGroup("group1");
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-1").start();

        new Thread(subGroup1, () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "group1-1").start();

        mainGroup.list();
        int activeCount = mainGroup.activeCount();
        Thread list[] = new Thread[activeCount];
        //递归
        int enumerate = mainGroup.enumerate(list);
        String result = StringUtils.join(list, ",");
        System.out.println(enumerate +"："+ result);
        Thread list2[] = new Thread[activeCount];
        enumerate = mainGroup.enumerate(list2, false);
        result = StringUtils.join(list2, ",");
        System.out.println(enumerate +"："+ result);
    }

    /**
     * setMaxPriority
     */
    public static void setMaxPriority(){
        ThreadGroup mainGroup = currentThread().getThreadGroup();
        System.out.println(mainGroup);
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(currentThread());
        }, "thread-1");
        thread.start();
        mainGroup.setMaxPriority(3);
        System.out.println(mainGroup);
        System.out.println(thread);
    }

    /**
     * 中断 线程组调用interrupt会使多有活动线程子线程组都中断
     */
    public static void interrupt() throws InterruptedException {
        ThreadGroup mainGroup = currentThread().getThreadGroup();
        new Thread(()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(currentThread());
                } catch (InterruptedException e) {
                    System.out.println(currentThread() + e.getMessage());
                    break;
                    //e.printStackTrace();
                }
            }
        },"main_group_t1").start();

        ThreadGroup subGroup = new ThreadGroup(mainGroup,"subGroup");
        new Thread(subGroup,()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(currentThread());
                } catch (InterruptedException e) {
                    System.out.println(currentThread() + e.getMessage());
                    break;
                    //e.printStackTrace();
                }
            }
        },"sub_group_t2").start();
        TimeUnit.MILLISECONDS.sleep(2);
        mainGroup.interrupt();
//        subGroup.interrupt();
    }

    public static void destory(){
        ThreadGroup mainGroup = currentThread().getThreadGroup();
        ThreadGroup subGroup = new ThreadGroup(mainGroup,"subGroup");
        mainGroup.list();
        subGroup.destroy();
        mainGroup.list();
    }

}
