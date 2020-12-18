package com.today.roc.go.verify;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.today.roc.go.common.utils.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        Boolean a = new Boolean(true);
        if (a){
            System.out.println(2);
        }
        if (a.booleanValue()){
            System.out.println(1);
        }
//        String value = "123456789";
//        Main main = new Main();
//        String result = main.desensitization(true, "证件号", value);
//        System.out.println(result);
        //testListToArray();
        //testDate();
        //testDoubleFormat();
        //test20201021();
        //test001();
//        Date a = null;
//        System.out.println(a.compareTo(new Date()) < 0);
    }

    public String desensitization(Boolean isFolderDesensitization, String fieldName , String fieldValue){
        //字段名为空或值为空直接返回空字符串
        if (StringUtils.isBlank(fieldName)||StringUtils.isBlank(fieldValue)){
            return Optional.ofNullable(fieldValue).orElse("");
        }
        //不脱敏直接返回
        if (!isFolderDesensitization){
            return fieldValue;
        }
        int begin =0;
        int length = fieldValue.length();
        int end = length;
        /**
         * 客户号的值，后5位用#覆盖
         * 账号的值，后5位用#覆盖
         * 卡号的值，第5到第8位用#覆盖
         * 证件号的值，第7到第10位用#覆盖
         */
        switch(fieldName){
            case "客户号":
                begin = length<5?0:length- 5;
                break;
            case "账号":
                begin = length<5?0:length- 5;
                break;
            case "卡号":
                begin = 4;
                end = 8;
                break;
            case "证件号":
                begin = 6;
                end = 10;
                break;
            default:
                return fieldValue;
        }
        fieldValue = dealReplace(fieldValue,begin,end,"#");
        return fieldValue;
    }

    public String dealReplace(String fieldValue , int begin , int end , String sign){
        StringBuffer value = new StringBuffer(fieldValue);
        StringBuffer sb = new StringBuffer();
        int length = fieldValue.length();
        if (begin<0){
            begin = 0;
        }
        if (length<begin){
            return fieldValue;
        }
        if (length<end){
            end = length;
        }
        IntStream.range(0,end-begin).forEach(
                v->{
                    sb.append(sign);
                }
        );
        fieldValue = value.replace(begin, end, sb.toString()).toString();
        return fieldValue;
    }

    public String replace(String fieldValue , int begin , int end , String sign){
        StringBuffer value = new StringBuffer(fieldValue);

        value.replace(begin,end,"#");

        //如果5-8位 <5位 不替换
        int length = fieldValue.length();
        if (fieldValue.length() <= begin){
            return fieldValue;
        }
        StringBuffer sb = new StringBuffer();
        //6位 替换5-7位
        if (length < end){
            fieldValue = fieldValue.substring(begin, begin);
            IntStream.range(begin,length).forEach(
                    v->{
                        sb.append(sign);
                    }
            );
            fieldValue += sb.toString();
        }else if (length == end){
            //8位 替换5-8位
            fieldValue = fieldValue.substring(begin, end);
            IntStream.range(begin,end).forEach(
                    v->{
                        sb.append(sign);
                    }
            );
            fieldValue += sb.toString();
        }else {
            //10位 替换5-8位

        }

        return fieldValue;
    }

    /**
     * List集合转数组，只取集合最后的一个位置数据
     */
    private static void testListToArray() {
        List<Integer> list = Lists.newArrayList(1,2,3);
        Integer[] integers = list.toArray(new Integer[1]);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

    /**
     * 一年时间判断
     */
    private static void testDate() {
        Date start = new Date(getMonthFirstTime(1999, 1));
        Date end = new Date(getMonthLastTime(1999, 12));
        System.out.println(DateUtil.getDate(start,DateUtil.DATE_TIME));
        System.out.println(DateUtil.getDate(end,DateUtil.DATE_TIME));
        //阳历一年365/366天
        Date addDate = DateUtil.addDate(start, 366);
        System.out.println("加一年："+DateUtil.getDate(addDate,DateUtil.DATE_TIME));
        if (addDate.compareTo(end)<0){
            System.out.println("时间范围请选择一年时间内");
        }
    }

    /**
     * BigDecimal转Double,Double格式化
     */
    private static void testDoubleFormat() {
        BigDecimal divide = BigDecimal.valueOf(134).divide(BigDecimal.valueOf(3600), 2, RoundingMode.HALF_UP);
        System.out.println(divide.doubleValue());
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        System.out.println(decimalFormat.format(divide.doubleValue()));
    }

    /**
     * 获取某年某月第一天时间戳
     *
     * @param year 年份
     * @return Date
     */
    public static Long getMonthFirstTime(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取某年某月最后一天时间戳
     *
     * @param year 年份
     * @return Date
     */
    public static Long getMonthLastTime(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 时间格式化+排序
     */
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

    /**
     * 判断是否有交集
     */
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
