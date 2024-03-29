package com.today.thread.线程池.queue;

import com.today.thread.线程池.QueueThread;

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
public class QueueServiceImpl implements QueueService {

    @Override
    public void exec(RejectedExecutionHandler rejectedExecutionHandler) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
                new SynchronousQueue<>(), rejectedExecutionHandler);
        for (int i = 0; i < 6; i++) {
            QueueThread queueThread = new QueueThread(1, TimeUnit.SECONDS,0);
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
        if (Objects.isNull(queueThread)) {
            exec(rejectedExecutionHandler);
        } else {
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
                    queue, rejectedExecutionHandler);
            for (int i = 0; i < 6; i++) {
                /*if (i<2){
                    poolExecutor.execute(new QueueThread());
                }else {
                    poolExecutor.execute(queueThread);
                }*/
                QueueThread thread = new QueueThread();
                thread.setPriority(i);
                thread.setSleepTime(queueThread.getSleepTime());
                thread.setUnit(queueThread.getUnit());
                poolExecutor.execute(thread);
            }
        }
    }
}
