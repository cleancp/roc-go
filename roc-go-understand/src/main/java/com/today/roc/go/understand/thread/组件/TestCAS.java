package com.today.roc.go.understand.thread.组件;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月23日 13:50*
 * log.info()
 */
public class TestCAS {
    /**
     * CAS ABA问题解决方式
     * AtomicStampedReference：时间戳 关注改过几次
     * AtomicMarkableReference：boolean 关注是否改过
     */
    public static void main(String[] args) throws InterruptedException {

        AtomicStampedReference<String> asr = new AtomicStampedReference("roc",1);

        String oldRef = asr.getReference();
        int oldStamp = asr.getStamp();
        System.out.println("reference： "+asr.getReference()+" stamp： " +asr.getStamp());

        Thread a = new Thread(()->{
            boolean b = asr.compareAndSet(oldRef, oldRef + "A", oldStamp, oldStamp + 1);
            System.out.println(Thread.currentThread().getName()+"reference： "+asr.getReference()+" stamp： " +asr.getStamp() +" result： " + b);
        },"AAAA");

        Thread b = new Thread(()->{
            boolean b1 = asr.compareAndSet(oldRef, oldRef + "B", oldStamp, oldStamp + 10);
            System.out.println(Thread.currentThread().getName()+"reference： "+asr.getReference()+" stamp： " +asr.getStamp() +" result： " + b1);
        },"BBBB");

        a.start();
        a.join();
        b.start();
        b.join();
        System.out.println("reference： "+asr.getReference()+" stamp： " +asr.getStamp());
    }

}
