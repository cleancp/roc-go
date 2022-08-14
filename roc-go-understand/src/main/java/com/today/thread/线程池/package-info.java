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
package com.today.thread.线程池;



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



 池化技术的思想：通过预先创建好多个线程，放在池中，这样可以在需要使用线程的时候直接获取，避免多次重复创建、销毁带来的开销

 * long corePoolSize：核心线程数，线程池活跃的最小线程数(即使是空闲状态)， 如果设置了allowCoreThreadTimeOut，则会超时销毁
 * long maximumPoolSize：线程池最大线程数 Integer.MAX_VALUE
 * long keepAliveTime：当线程数大于核心数时，这是多余的空闲线程在终止之前等待新任务的最长时间（实际应用都会转为纳秒）
 * TimeUnit unit：keepAliveTime的单位
 * BlockingQueue<Runnable> workQueue：在执行任务之前用于保留任务的队列。该队列将仅保存由{@code execute}方法提交的{@code Runnable} 任务。
 * 队列分类：
 * 直接提交队列、SynchronousQueue，提交的任务不会被缓存，而是直接执行，若用于执行任务的线程数量大于maximumPoolSize，执行拒绝策略。适合已经准确知道并发量的业务使用。
 * 有界任务队列、ArrayBlockingQueue，提交的任务小于corePoolSize，则会继续创建线程直到大于corePoolSize，大于corePoolSize会放到等待队列中，直到超过等待队列的容量，
 *              新任务将会继续创建线程，直到大于maximumPoolSize，执行拒绝策略。
 *              线程数量的上限与有界任务队列的状态有直接关系，如果任务数量小于等待队列容量或者任务队列容量很大，那线程数量上限小于corePoolSize,反之当等待队列满时，
 *              以maximumPoolSize为线程数量上限
 * 无界任务队列、LinkedBlockingQueue，使用无界队列，如果任务数小于corePoolSize，则会继续创建线程直到等于corePoolSize，大于corePoolSize队列会一直增加任务，
 *              线程数量上限为corePoolSize，此时maximumPoolSize参数无效，业务上使用无界队列需要注意，如果任务数一直增长，而任务执行慢，长时间可能导致太多任务占满资源
 * 优先任务队列、PriorityBlockingQueue，特殊的无界队列，会按照优先级高低执行
 * ThreadFactory：线程工厂，定义线程属性
 * Handler：拒绝策略，当新线程过来超过线程池容量，会执行拒绝策略
 * 策略分类：
 * ThreadPoolExecutor.AbortPolicy：直接抛出异常，显示被拒绝线程信息以及线程池配置
 * ThreadPoolExecutor.CallerRunsPolicy：把任务给调用者线程执行
 * ThreadPoolExecutor.DiscardOldestPolicy：丢弃最早加入队列的任务，重新提交当前任务
 * ThreadPoolExecutor.DiscardPolicy：不做任何处理，丢弃任务


 通过对ctl的运算，能够得到两个重要的变量，workerCount(worker线程数量)和runState(线程池运行状态)。

 线程池运行状态
 RUNNING:  接收新任务并且执行队列任务
 SHUTDOWN: 不接收新任务但是执行队列任务
 STOP: 不接收新任务不执行队列任务 ,并且中断进行中的任务
 TIDYING: 所有任务都已被终止, 线程数为0,
 线程转换为状态TIDYING时将执行terminated()钩子方法( 自定义实现)
 TERMINATED: terminated()已经执行完成




 */