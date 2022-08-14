package com.today.utils.concurrent;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.List;
import java.util.Set;
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
 * @createTime 2020年07月30日 09:15*
 * log.info()
 */
public class parallelStreamTest {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        List<Integer> list = Lists.newArrayList(10000);
        Set<Integer> set = Sets.newConcurrentHashSet();
        Set<Integer> set2 = Sets.newConcurrentHashSet();
        List<Integer> cfList = Lists.newArrayList();
        List<Integer> cf2List = Lists.newArrayList();
        AtomicInteger integer1 = new AtomicInteger();
        AtomicInteger integer2 = new AtomicInteger();
        //Map<Integer,Integer> map = Maps.newHashMap();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        Long start1 = System.currentTimeMillis();
        list.parallelStream().forEach(i -> {
            cfList.add(i);
            set.add(i);
            System.out.print(i + " ");
            integer1.addAndGet(1);
        });
        System.out.println();
        System.out.println(System.currentTimeMillis() - start1);
        System.out.println("--------------------------------");
        Long start2 = System.currentTimeMillis();
        Collections.synchronizedList(list).parallelStream().forEach(i -> {
            cf2List.add(i);
            set2.add(i);
            System.out.print(i + " ");
            integer2.addAndGet(1);
        });
        System.out.println();
        System.out.println(System.currentTimeMillis() - start2);

        //        Map<Integer, List<Integer>> collect = cfList.stream()
        //            .collect(Collectors.groupingBy(a->a));
        //        collect.forEach((key, value) -> {
        //            if (value.size()>1){
        //                System.out.println(key+""+value.size());
        //            }
        //        });并发
        System.out.println(set.size());
        System.out.println(integer1.get());
        System.out.println("=====================================");
        //        collect = cf2List.stream()
        //                .collect(Collectors.groupingBy(a->a));
        //        collect.forEach((key, value) -> {
        //            if (value.size()>1){
        //                System.out.println(key+""+value.size());
        //            }
        //        });
        System.out.println(set2.size());
        System.out.println(integer1.get());
    }
}
