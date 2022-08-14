package com.today.thread.高并发编程详解.JVM类加载器.概念;

import com.today.thread.高并发编程详解.JVM类加载器.BrokeDelegateClassLoader;

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
public class InitClassClassLoader {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        BrokeDelegateClassLoader classLoader = new BrokeDelegateClassLoader();
        Class<?> aClass = classLoader.loadClass("com.today.thread.高并发编程详解.JVM类加载器.概念.SimpleClass");

        System.out.println(classLoader.getParent());
        Object o = aClass.newInstance();
    }
}
