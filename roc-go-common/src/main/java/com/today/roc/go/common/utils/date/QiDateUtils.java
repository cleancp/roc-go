package com.today.roc.go.common.utils.date;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @program zlsd
 * @description: 当前日期工具
 * @author: LiuHang
 * @create: 2020/03/03 15:39
 * @Copyright: 2018 www.mujinkeji.com Inc. All rights reserved.
 */

public class QiDateUtils {
    /**
     * 获取当月开始时间戳
     *
     * @param timeZone 如 GMT+8:00
     * @return
     */
    public static Long getMonthStartTime(Date date, String timeZone) {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当月的结束时间戳
     *
     * @param timeZone 如 GMT+8:00
     * @return
     */
    public static Long getMonthEndTime(Date date, String timeZone) {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));// 获取当前月最后一天
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取某年某月第一天时间
     *
     * @param year 年份
     * @return Date
     */
    public static Date getMonthFirstTime(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getTime();
    }


    /**
     * 获取某年某月最后一天时间
     *
     * @param year 年份
     * @return Date
     */
    public static Date getMonthLastTime(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Long getWeekStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static Long getWeekEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        Long monthStartTime = getWeekStartTime(date);
        calendar.setTimeInMillis(monthStartTime);
        calendar.add(Calendar.DATE, 6);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 将数字秒 转为 时间表示 时分秒
     *
     * @param num
     * @return
     */
    public static String number2Time(Integer num) {
        StringBuffer sb = new StringBuffer();
        //秒
        int sec = num % 60;
        //分
        int min = num / 60 % 60;
        //时
        int hour = num / 3600 % 60;
        sb = hour < 10 ? sb.append("0" + hour) : sb.append(hour);
        sb.append(":");
        sb = min < 10 ? sb.append("0" + min) : sb.append(min);
        sb.append(":");
        sb = sec < 10 ? sb.append("0" + sec) : sb.append(sec);
        return sb.toString();
    }
}
