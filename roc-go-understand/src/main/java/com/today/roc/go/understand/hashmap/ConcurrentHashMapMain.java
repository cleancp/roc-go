package com.today.roc.go.understand.hashmap;

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
 * @createTime 2020年09月22日 15:43*
 * log.info()
 */
public class ConcurrentHashMapMain {

    /**
     * JDK 8 中采用的是位桶 + 链表/红黑树的方式，当某个位桶的链表的长度超过 8 的时候，这个链表就将转换成红黑树
     * HashMap 不会因为多线程 put 导致死循环（JDK 8 用 head 和 tail 来保证链表的顺序和之前一样；JDK 7 rehash 会倒置链表元素），
     * 但是还会有数据丢失等弊端（并发本身的问题）。因此多线程情况下还是建议使用 ConcurrentHashMap
     *
     * HashMap 在并发时可能出现的问题主要是两方面：
     * 如果多个线程同时使用 put 方法添加元素，而且假设正好存在两个 put 的 key 发生了碰撞（根据 hash 值计算的 bucket 一样），
     * 那么根据 HashMap 的实现，这两个 key 会添加到数组的同一个位置，这样最终就会发生其中一个线程 put 的数据被覆盖
     *
     * 如果多个线程同时检测到元素个数超过数组大小 * loadFactor，这样就会发生多个线程同时对 Node 数组进行扩容，都在重新计算元素位置以及复制数据，
     * 但是最终只有一个线程扩容后的数组会赋给 table，也就是说其他线程的都会丢失，并且各自线程 put 的数据也丢失
     */

    public static void main(String[] args) {

    }

}
