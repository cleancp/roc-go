package com.today.java8._05date;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年06月14日 22:24*
 * log.info()
 */
public class _05Main {
    public static void main(String[] args) {

        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.instant());
        ZoneId zoneId = ZoneId.systemDefault();
        //Asia/Shanghai
        System.out.println(zoneId.getId());
        ZoneId of = ZoneId.of("America/New_York");
        LocalDateTime now1 = LocalDateTime.now(of);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("America/New_York：" + now1.format(formatter));
        CharSequence sequence = "2021-06-14 10:33:00";
        LocalDateTime parse = LocalDateTime.parse(sequence, formatter);
        System.out.println(parse.format(formatter));
    }
}
