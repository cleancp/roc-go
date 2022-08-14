package com.today;

import com.today.utils.date.DateUtil;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月03日 09:50*
 * log.info()
 */
public class CalendarTest {

    public static void main(String[] args) throws ParseException {
        Date start;
        Date end;
        String date1 = "2020-12";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//        Date date = sdf.parse(date1);
        Calendar curr = Calendar.getInstance();
        System.out.println(DateUtil.getDate(curr.getTime(), DateUtil.DATE_TIME));
        curr.clear();
        System.out.println(DateUtil.getDate(curr.getTime(), DateUtil.DATE_TIME));
        curr.setTime(new Date());
//        curr.add(Calendar.DAY_OF_MONTH, -1);

        curr.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println(DateUtil.getDate(curr.getTime(), DateUtil.DATE_TIME));

        curr.set(Calendar.DAY_OF_MONTH, 0);
        System.out.println(DateUtil.getDate(curr.getTime(), DateUtil.DATE_TIME));

        curr.set(Calendar.DAY_OF_MONTH, -1);
        System.out.println(DateUtil.getDate(curr.getTime(), DateUtil.DATE_TIME));
//        int day = curr.get(Calendar.DAY_OF_MONTH);
//        int year = curr.get(Calendar.YEAR);
//        int month = curr.get(Calendar.MONTH) + 1;
//        System.out.println(year);
//        System.out.println(month);
//        System.out.println(day);
//        start = QiDateUtils.getMonthFirstTime(year, month);
//        end = QiDateUtils.getMonthLastTime(year, month);
//        System.out.println(DateUtil.getDate(curr.getTime(), DateUtil.DATE_TIME));
        start = DateUtil.getMonningDate(curr.getTime());
        end = DateUtil.getEveningDate(curr.getTime());
        System.out.println(DateUtil.getDate(start, DateUtil.DATE_TIME));
        System.out.println(DateUtil.getDate(end, DateUtil.DATE_TIME));
    }
}
