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
 * @createTime 2020年09月23日 16:51*
 * log.info()
 */
package com.today.roc.go.understand.hashmap;
/**
 1、悲观锁与乐观锁：
 悲观锁是指如果一个线程占用了一个锁，而导致其他所有需要这个锁的线程进入等待，一直到该锁被释放，换句话说就是这个锁被独占，比如说典型的就是synchronized；
 乐观锁是指操作并不加锁，而是抱着尝试的态度去执行某项操作，如果操作失败或者操作冲突，那么就进入重试，一直到执行成功为止。

 2、原子性，指令有序性和线程可见性
 原子性和事务的原子性一样，对于一个操作或者多个操作，要么都执行，要么都不执行。指令有序性是指，在我们编写的代码中，上下两个互不关联的语句不会被指令重排序。
 指令重排序是指处理器为了性能优化，在无关联的代码的执行是可能会和代码顺序不一致。比如说int i = 1；int j = 2；那么这两条语句的执行顺序可能会先执行int j = 2；
 线程可见性是指一个线程修改了某个变量，其他线程能马上知道。

 3、无锁算法（nonblocking algorithms）
 使用低层原子化的机器指令， 保证并发情况下数据的完整性。典型的如CAS算法。

 4、内存屏障
 在《深入理解JVM》中解释是：它确保指令重排序时不会把其后面的指令排到内存屏障之前的位置，也不会把前面的指令排到内存屏障的后面；
 即在执行到内存屏障这句指令时，在它前面的操作已经全部完成；它会强制将对缓存的修改操作立即写入主存；如果是写操作，它会导致其他CPU中对应的缓存行无效。
 在使用volatile修饰的变量会产生内存屏障（后面会详细解释）。

 5、工作内存-主内存
 这个访问规则局限于对象实例字段，静态字段等，局部变量不包括在内，因为局部变量不存在竞争问题。
 volatile修饰的变量具有可见性与有序性。

 */