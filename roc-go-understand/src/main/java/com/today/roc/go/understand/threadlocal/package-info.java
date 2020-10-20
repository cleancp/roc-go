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
 * @createTime 2020年10月15日 21:04*
 * log.info()
 */
package com.today.roc.go.understand.threadlocal;


/**
 *https://developer.aliyun.com/article/689656
 *
 * 1、什么是内存泄漏，内存溢出？
 * 内存泄漏：无法释放已申请的内存：画画的时候规定用铅笔画在纸上，可以擦掉，但是有人用彩笔画没法擦掉，这些没法擦掉的就是泄漏了没法回收再使用
 * 内存溢出(内存越界)：申请不到足够的内存：画画的时候，大家都在纸上画，但是画的空间不够了，结果画在纸外，画在纸外的一笔就导致溢出了
 * 调用栈溢出/缓冲区溢出
 *
 * 2、ThreadLocal是什么？
 * ThreadLocal是线程变量，也就是说ThreadLocal创建的变量是属于当前线程的，因为线程间隔离，所以是线程私有的，
 * ThreadLocal为变量在每个线程中创建了一个副本，线程可以访问自己的那个副本
 *
 * 3、主要用途？
 *      1、线程跨层的数据传输 ：私有化拦截器跨层数据传输
 *      2、线程间数据隔离 ：不同登录用户的操作人ID不同
 *      3、进行事务操作，用于存储线程事务信息
 *      4、数据库连接，Session会话管理
 *
 * ThreadLocalMap 是ThreadLocal的静态内部类，key就是this(ThreadLocal) value 是当前线程设置的值
 * ThreadLocalMap里面有一个内部类Entry extends WeakReference<ThreadLocal<?>> key就是ThreadLocal，所以key是个弱引用，会被垃圾回收，
 * 当key被垃圾回收，value便没有指向，但是value又是个强引用，
 * 扩展：WeakReference就是弱引用，相对应的就是强引用，当进行GC时不管有没有指向，弱引用都会被回收
 *
 * 4、内存泄漏问题？
 * 因为ThreadLocal是弱引用且作为ThreadLocalMap的key，如果key被回收为空，此时导致value不会被使用到，同时强引用也无法被回收，分配的内存无法回收造成内存泄漏
 * 虽然ThreadLocal在下一次调用ThreadLocalMap get() set() remove()会清除key为null的数据，但是如果在处理过程中没有调用到还是会存在内存泄漏的情况
 * 解决方案：每次使用完ThreadLocal，都调用它的remove()方法，清除数据。
 *
 * */
