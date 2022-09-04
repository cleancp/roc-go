package com.tuling.concurrency.course7_synchrinized;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2022年09月03日 22:59*
 * log.info()
 */
public class ObjectMonitorLearn {

    /**
     * ObjectMonitor() {
     *     _header       = NULL; //对象头  markOop
     *     _count        = 0;
     *     _waiters      = 0,
     *     _recursions   = 0;   // 锁的重入次数
     *     _object       = NULL;  //存储锁对象
     *     _owner        = NULL;  // 标识拥有该monitor的线程（当前获取锁的线程）
     *     _WaitSet      = NULL;  // 等待线程（调用wait）组成的双向循环链表，_WaitSet是第一个节点
     *     _WaitSetLock  = 0 ;
     *     _Responsible  = NULL ;
     *     _succ         = NULL ;
     *     _cxq          = NULL ; //多线程竞争锁会先存到这个单向链表中 （FILO栈结构）
     *     FreeNext      = NULL ;
     *     _EntryList    = NULL ; //存放在进入或重新进入时被阻塞(blocked)的线程 (也是存竞争锁失败的线程)
     *     _SpinFreq     = 0 ;
     *     _SpinClock    = 0 ;
     *     OwnerIsThread = 0 ;
     *     _previous_owner_tid = 0;
     *   }
     */

}
