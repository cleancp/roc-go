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
 * @createTime 2020年09月21日 09:27*
 * log.info()
 */
package com.today.roc.go.understand;



/**
 竞态条件(race condition)：两个或多个线程同时对一共享数据进行修改，从而影响程序运行的正确性。
 共享(Shared)的 和 可变(Mutable)才会出现竟态条件

 synchronized
 对方法进行加锁，确保多个线程中只有一个线程执行方法；
 对某个对象实例（在我们上面的探讨中，变量可以使用对象来替换）进行加锁，确保多个线程中只有一个线程对对象实例进行访问；
 对类对象进行加锁，确保多个线程只有一个线程能够访问类中的资源。

 回调地狱：设计许多嵌套回调处理程序的代码。回调地狱很难追踪 debug。


 Java 中，创建线程的方式主要有三种
 通过继承 Thread 类来创建线程
 通过实现 Runnable 接口来创建线程
 通过 Callable 和 Future 来创建线程











 */