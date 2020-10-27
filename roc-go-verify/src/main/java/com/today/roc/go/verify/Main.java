package com.today.roc.go.verify;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.today.roc.go.common.utils.date.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

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

        BigDecimal divide = BigDecimal.valueOf(134).divide(BigDecimal.valueOf(3600), 2, RoundingMode.HALF_UP);
        System.out.println(divide.doubleValue());
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        System.out.println(decimalFormat.format(divide.doubleValue()));

        //test20201021();
        //test001();
    }

    private static void test20201021() {
        Date date1 = DateUtil.parseDate("202009", DateUtil.DATE_MOTH);
        System.out.println(date1);
        Date date2 = DateUtil.parseDate("20200900",DateUtil.DATE);
        System.out.println(date2);

        long l1 = Double.valueOf("22.0").longValue();
        System.out.println(l1);
        List<Integer> list = Lists.newArrayList(1,2,4,8,3,0,9);
        System.out.println(list);
        List<Integer> collect = list.stream().sorted(Comparator.comparing(a -> a)).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(collect);
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
