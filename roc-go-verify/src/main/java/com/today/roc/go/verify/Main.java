package com.today.roc.go.verify;

import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
 * @createTime 2020年10月20日 10:22*
 * log.info()
 */
public class Main {

    public static void main(String[] args) {
        test001();
    }

    private static void test001() {
        Set<Long> set1 = Sets.newHashSet();
        Set<Long> set2 = Sets.newHashSet();
        set2.add(1L);
        List<Long> intersection = set1.stream().filter(item -> set2.contains(item)).collect(Collectors.toList());
        System.out.println(intersection.size() > 0);
        Long a = 1L;
        Long c = 1L;
        System.out.println(a.equals(c));
    }

}
