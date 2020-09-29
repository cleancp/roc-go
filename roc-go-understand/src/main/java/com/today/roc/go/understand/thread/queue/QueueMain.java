package com.today.roc.go.understand.thread.queue;

import com.today.roc.go.understand.thread.QueueThread;
import com.today.roc.go.understand.thread.policy.CustomizeRejectedExecutionHandler;

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
 * @createTime 2020年09月29日 10:36*
 * log.info()
 */
public class QueueMain {

    public static void main(String[] args) {
        //直接提交队列
        QueueService service = new QueueServiceImpl();
        QueueThread queueThread = new QueueThread(1, TimeUnit.SECONDS);
        execDiffQueueDiffPolicy(service, new SynchronousQueue(), queueThread, 5);
    }

    public static void execDiffQueueDiffPolicy(QueueService service, BlockingQueue queue, QueueThread queueThread, int... indexs) {
        RejectedExecutionHandler policy = null;
        for (int i = 0; i < indexs.length; i++) {
            int index = indexs[i];
            switch (index) {
                case 1:
                    //如果超出最大线程数触发拒绝策略，会报错
                    policy = new ThreadPoolExecutor.AbortPolicy();
                    break;
                case 2:
                    //如果队列超出最大线程数，会把任务给调用者的线程执行
                    policy = new ThreadPoolExecutor.CallerRunsPolicy();
                    break;
                case 3:
                    //抛弃最老的(最早放进去的的线程)，然后再次提交当前任务 (如果要抛弃的任务还没执行完会报栈溢出)
                    policy = new ThreadPoolExecutor.DiscardOldestPolicy();
                    break;
                case 4:
                    //该策略会默默丢弃无法处理的任务，不予任何处理。当然使用此策略，业务场景中需允许任务的丢失
                    policy = new ThreadPoolExecutor.DiscardPolicy();
                    break;
                case 5:
                    policy = new CustomizeRejectedExecutionHandler();
                    break;
                default:
                    break;
            }
            service.exec(policy, queue, queueThread);
            System.out.println("----------------------------------------");
        }
    }

}
