package com.today.thread.高并发编程详解.JVM类加载器;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月06日 22:21*
 * log.info()
 */
public class ExtClassLoader {

    public static void main(String[] args) {
        String[] paths = System.getProperty("java.ext.dirs").split(";");
        for (String path : paths) {
            System.out.println(path);
        }
    }
}
