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
package com.today.hashmap;
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

 公平锁：获取锁的线程是根据排队先后顺序固定的 synchronized
 非公平锁：获取锁的线程先后排队顺序是可以变化的，可以随意插队，根据CPU的调度来确认哪个线程优先获取 ReentrantLock(可以设置公平锁，也可以设置非公平锁，默认是非公平锁)

 死锁：两个及以上线程和两个及以上的共享变量，线程间获得变量的锁相斥，导致两个线程不能获取另一个线程持有的锁，互相阻塞
 活锁：线程已获取资源锁情况下，要获取其它资源锁，如果获取失败，释放已有资源锁，再整个流程重新获取,耗费资源

        线程获取锁的步骤是两步
            1、判断锁的状态，如果是空闲状态走2
            2、将锁设置为当前线程持有
 互斥锁：当线程加锁失败时，内核会把线程的状态从「运行」状态设置为「睡眠」状态，然后把 CPU 切换给其他线程运行；
        接着，当锁被释放时，之前「睡眠」状态的线程会变为「就绪」状态，然后内核会在合适的时间，把 CPU 切换给该线程运行。
 自旋锁：CAS (Compare And Swap)，CAS指令是将线程获取锁的步骤合并为一个硬件级指令，原子指令，满足原子性
        当多个线程抢占锁时，如果锁被其它线程占用，会一直循环当前线程去获取锁，处于忙等待状态，直到处理成功
        一直自旋，利用 CPU 周期，直到锁可用

        当加锁失败时，互斥锁用「线程切换」来应对，自旋锁则用「忙等待」来应对
 用途：互斥锁会产生两次上下文切换，时间大约是几十纳秒-几微秒，如果要执行的业务耗时长，可以使用该方式
        CAS适合在耗时成正比（即业务耗时少）的时候使用

 读写锁：
    当「写锁」没有被线程持有时，多个线程能够并发地持有读锁，这大大提高了共享资源的访问效率，因为「读锁」是用于读取共享资源的场景，所以多个线程同时持有读锁也不会破坏共享资源的数据。
    但是，一旦「写锁」被线程持有后，读线程的获取读锁的操作会被阻塞，而且其他写线程的获取写锁的操作也会被阻塞。

    读优先锁：线程A获取读锁成功，此时线程B要获取写锁，会阻塞，此时线程C获取读锁能成功，当线程AC执行完之后，线程B才能获取写锁。
            存在问题就是一直有线程读，那写线程将会一直等待
    写优先锁：线程A获取读锁成功，此时线程B要获取写锁，会阻塞，此时线程C获取读锁也阻塞，当线程A执行完之后，线程B能立马获取写锁。
            存在问题就是一直有线程写，那读线程将会一直等待
    公平读写锁：使用队列将线程排序，按照先进先出的原则，不偏向读和写，按照顺序读或写
    用途：读写锁适用于能明确区分读操作和写操作的场景。
 */