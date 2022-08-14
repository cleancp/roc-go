package com.today.字符串;

import java.util.concurrent.TimeUnit;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年04月21日 18:52*
 * log.info()
 */
public class StringTest {

    public static void main(String[] args) throws InterruptedException {
        String str = new String("a");
        for (int i = 0; i < 100000; i++) {
            TimeUnit.MILLISECONDS.sleep(1);
            str = str+i;
        }

        String a = "1"+"2"+"3"+"4";
        String b = "2";
        System.out.println(a+b);
        System.out.println((new StringBuilder()).append(a).append(b).toString());
    }

}
