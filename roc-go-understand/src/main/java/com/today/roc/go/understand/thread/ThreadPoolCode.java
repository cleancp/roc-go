package com.today.roc.go.understand.thread;

import java.util.concurrent.atomic.AtomicInteger;

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
 * @createTime 2020年09月27日 14:49*
 * log.info()
 */
public class ThreadPoolCode {
    //-536870912
    private final        AtomicInteger ctl        = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int           COUNT_BITS = Integer.SIZE - 3;
    private static final int           CAPACITY   = (1 << COUNT_BITS) - 1;//536870911

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;//-536870912
    private static final int SHUTDOWN   =  0 << COUNT_BITS;//0
    private static final int STOP       =  1 << COUNT_BITS;//536870912
    private static final int TIDYING    =  2 << COUNT_BITS;//1073741824
    private static final int TERMINATED =  3 << COUNT_BITS;//1610612736

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    public static void main(String[] args) {

    }

}
