package com.today.thread.forkjoin;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Software License Declaration.
 * <p>
 * zhilingsd.com, Co,. Ltd.
 * Copyright © 2016 All Rights Reserved.
 * <p>
 * Copyright Notice
 * This documents is provided to zhilingsd contracting agent or authorized programmer only.
 * This source code is written and edited by zhilingsd Co,.Ltd Inc specially for financial
 * business contracting agent or authorized cooperative company, in order to help them to
 * install, programme or central control in certain project by themselves independently.
 * <p>
 * Disclaimer
 * If this source code is needed by the one neither contracting agent nor authorized programmer
 * during the use of the code, should contact to zhilingsd Co,. Ltd Inc, and get the confirmation
 * and agreement of three departments managers  - Research Department, Marketing Department and
 * Production Department.Otherwise zhilingsd will charge the fee according to the programme itself.
 * <p>
 * Any one,including contracting agent and authorized programmer,cannot share this code to
 * the third party without the agreement of zhilingsd. If Any problem cannot be solved in the
 * procedure of programming should be feedback to zhilingsd Co,. Ltd Inc in time, Thank you!
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年12月18日 11:09*
 * log.info()
 */
@Slf4j
public class ForkInCollection extends RecursiveAction {

    private List<Integer> reqs;
    private List<Integer> result;
    private int           fromIndex;//开始统计的下标
    private int           toIndex;//统计到哪里结束的下标
    private int           threshold;


    static class ForkJoinUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            log.info(t.getName(),e);
        }
    }

    public static void main(String[] args) {
        List<Integer> cusResult = Lists.newArrayList();
        ForkInCollection fork = new ForkInCollection(cusResult, 0, 100, 5);
        //ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors(),new ForkJoinPool.DefaultForkJoinWorkerThreadFactory(), new ForkJoinUncaughtExceptionHandler(),false);
        //ForkJoinPool.commonPool().invoke(fork);
        ForkJoinPool.commonPool().submit(fork);
        log.info("join begin");
        log.info("join after");
        log.info("结果：{}"+cusResult,cusResult.size());
    }

    @Override
    protected void compute() {
        log.info(this.toString());
        if (toIndex - fromIndex < threshold) {
            for (int i = fromIndex; i <= toIndex; i++) {
                doSomething();
                result.add(i);
            }
        } else {
            //fromIndex....mid....toIndex
            //1...................70....100
            int mid = (fromIndex + toIndex) / 2;
            ForkInCollection left = new ForkInCollection(result, fromIndex, mid, threshold);
            ForkInCollection right = new ForkInCollection(result, mid + 1, toIndex, threshold);
            invokeAll(left, right);
            left.join();
            right.join();
        }
    }

    private void doSomething() {
//        System.out.println(1/0);
    }

    public ForkInCollection(List<Integer> result, int fromIndex, int toIndex, int threshold) {
        this.result = result;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.threshold = threshold;
    }

    public ForkInCollection(List<Integer> reqs, List<Integer> result, int fromIndex, int toIndex, int threshold) {
        this.reqs = reqs;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.threshold = threshold;
        this.result = result;
    }

    @Override
    public String toString() {
        return "ForkInCollection{" +
                ", fromIndex=" + fromIndex +
                ", toIndex=" + toIndex +
                '}';
    }
}
