package com.today.roc.go.understand.thread.queue;

import com.today.roc.go.understand.thread.QueueThread;

import java.util.Objects;
import java.util.concurrent.*;

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
 * @createTime 2020年09月29日 09:56*
 * log.info()
 */
public class SynchronousQueueServiceImpl implements QueueService {

    public static void main(String[] args) {

//        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
//            @Override
//            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                System.out.println(Thread.currentThread().getName() + " 执行拒绝策略");
//            }
//        };
//
//        //中止策略
//        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        new SynchronousQueueServiceImpl().exec(callerRunsPolicy);
    }

    @Override
    public void exec(RejectedExecutionHandler rejectedExecutionHandler) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
                new SynchronousQueue<>(), rejectedExecutionHandler);
        for (int i = 0; i < 6; i++) {
            QueueThread queueThread = new QueueThread(1, TimeUnit.SECONDS);
            poolExecutor.execute(queueThread);
        }
    }

    @Override
    public void exec(RejectedExecutionHandler rejectedExecutionHandler, QueueThread queueThread) {
        if (Objects.isNull(queueThread)) {
            exec(rejectedExecutionHandler);
        } else {
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
                    new SynchronousQueue<>(), rejectedExecutionHandler);
            for (int i = 0; i < 6; i++) {
                if (i<2){
                    poolExecutor.execute(new QueueThread());
                }else {
                    poolExecutor.execute(queueThread);
                }
            }
        }
    }

    @Override
    public void exec(RejectedExecutionHandler rejectedExecutionHandler, BlockingQueue queue, QueueThread queueThread) {

    }
}
