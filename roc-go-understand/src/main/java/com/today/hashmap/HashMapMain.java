package com.today.hashmap;

import java.util.HashMap;

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
 * @createTime 2020年09月21日 09:28*
 * log.info()
 */
public class HashMapMain {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {

        /**
         * new：设置负载因子为默认0.75  capacity()方法获取值
         */
        HashMap map = new HashMap();
        /**
         * 获取key的hash值，通过 i = (n - 1) & hash 确定key所在数组位置(n为数组长度)，如果位置为空直接放值
         * 扩容结果为2次幂与使用n-1进行索引位置判断好处：
         * 1、2次幂-1，低位全为1，由低位hash值确认位置，这个确认相对是唯一的，只受hash值的最高位一位的差异影响，发生hash碰撞的概率相对小。
         * 2、在扩容时：而扩容后只有一位差异，也就是多出了最左位的1，这样在通过 h&(length-1)的时候，只要h对应的最左边的那一个差异位为0
         * 就能保证得到的新的数组索引和老数组索引一致(大大减少了之前已经散列良好的老数组的数据位置重新调换)
         * 3、如果低位不全是1 ，使用&运算，低位非1的时候，该位其实失去了离散功能，增加了hash碰撞
         * 否则
         * 1、如果key相等直接覆盖
         * 2、节点类型为树节点TreeNode，设值
         * 3、该节点有单链数据，设置最后的单链指向当前key，如果该单链长度 binCount >= TREEIFY_THRESHOLD - 1(TREEIFY_THRESHOLD:树化阈值) 则进行树化操作
         * 树化阈值基于泊松分布（单位时间（或空间）内随机事件发生的次数）定义，平均参数为 0.5 ，进行树化数组最小长度为 MIN_TREEIFY_CAPACITY=64
         * 通过 i = (n - 1) & hash 确定key所在数组位置(n为数组长度)，将该位置下的链表节点转树节点，首节点调用hd.treeify(tab);
         */
        /**1的ascii码=49 hash值=49？*/
        /**
         * 扩容时，if oldCap > 0，判断是否超过最大长度，设置最大2^30，未超过新阈值与新数组长度扩展一倍 (此时如果老阈值为0 右移会溢出导致新阈值为0)
         * else if oldThr > 0 设置 newCap = oldThr; else 阈值与数组长度设置默认值
         * if 新阈值为 0 设置 新阈值为 新数组长度*加载因子
         *
         * 扩容时移动老数组数据至新数组，如果e.hash & oldCap == 0 ，索引位置不变，否则新索引位置=原索引+oldCap
         *
         * jdk1.7 HashMap死循环，扩容时，链表数据反转，并发情况下出现死循环情况
         * 原数组 3 ， 7， 5
         * 线程A B跑 ， A跑到3,下一个为7
         * B跑完变成7、3
         * A再跑,设置链表头节点为3,next为7，7放在3前，正常情况下7后面没有next，但是读取主内存读到B线程跑的数据，7的next为3,
         * A现在为7，3 ， 现在跑7的next又为3，3的next指向7，造成循环
         * 此时3对应3产生循环链表，在调用get方法的时候造成死循环
         */
        map.put("1", "one");
        /**
         * 区分不同节点类型
         */
        map.get("1");
        //不能只通过key来判断相等，需要用hashcode与equals一起判断，要重写hashcode方法
        HashMap map1 = new HashMap();
        map1.put(new User("1",1L),"1");
        Object o = map1.get(new User("1", 1L));
        System.out.println(o);
    }

    /**
     * 获取大于等于传入进来的数值的最小二次幂值
     * > 2^n ，-1 最高位2^n  ， 所有低位 | = 1 ，最后+1 ，进位2^(n+1)
     * <= 2^n , -1 最高位2^(n-1) , 所有低位| = 1 ，+1 进位2^(n+1)
     *
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     * key的hash算法
     * 异或 相同为假 不同为真
     *
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

}
