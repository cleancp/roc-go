package com.today.roc.go.understand.thread.高并发编程详解.JVM类加载器.概念;

import java.util.ArrayList;
import java.util.List;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月07日 13:45*
 * log.info()
 */
public class SimpleClass {

    private static byte[] buffer = new byte[8];

    private static String str = "";

    private static List<String> list = new ArrayList<>();

    static {
        buffer[0] = 1;
        str = "simple";
        list.add("element");
        System.out.println(buffer[0]);
        System.out.println(str);
        System.out.println(list.get(0));
    }

    public static void main(String[] args) {

    }
}
