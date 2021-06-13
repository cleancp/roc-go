package com.today.roc.go.understand.字符串;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年04月26日 23:42*
 * log.info()
 */
public class StringHashTest {

    public static void main(String[] args) {
        String a = new String("Ma");
        String b = new String("NB");
        System.out.println(a.hashCode() == b.hashCode());

    }

}
