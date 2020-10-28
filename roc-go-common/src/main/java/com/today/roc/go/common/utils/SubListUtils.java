package com.today.roc.go.common.utils;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.IntStream;

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
 * @createTime 2020年10月28日 20:49*
 * log.info()
 */
public class SubListUtils {

    public static  <T> List<T> subList(Integer currentPage, Integer pageSize, List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        int fromIndex = (currentPage - 1) * pageSize;
        int toIndex = currentPage * pageSize;
        int total = list.size();
        if (total < fromIndex) {
            return Lists.newArrayList();
        }
        if (total < toIndex) {
            toIndex = total;
        }
        //截取数据包括fromIndex ， 不包括toIndex
        List<T> result = list.subList(fromIndex, toIndex);
        return result;
    }


    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        IntStream.range(1,21).forEach(
                v->{
                    list.add(v);
                }
        );
        System.out.println(list.size());
        List<Integer> subList = subList(2, 10, list);
        subList.forEach(
                a->{
                    System.out.println(a);
                }
        );
    }
}
