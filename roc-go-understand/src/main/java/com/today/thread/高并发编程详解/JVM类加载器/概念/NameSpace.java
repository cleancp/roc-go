package com.today.thread.高并发编程详解.JVM类加载器.概念;

import com.today.thread.高并发编程详解.JVM类加载器.BrokeDelegateClassLoader;
import com.today.thread.高并发编程详解.JVM类加载器.MyClassLoader;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月07日 12:11*
 * log.info()
 */
public class NameSpace {

    public static void main(String[] args) throws ClassNotFoundException {
        /***系统类加载器加载同一个类 */
        ClassLoader classLoader = NameSpace.class.getClassLoader();
        Class<?> aClass = classLoader.loadClass("com.today.lock.LockThisMain");
        Class<?> bClass = classLoader.loadClass("com.today.lock.LockThisMain");
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
        System.out.println(aClass == bClass);

        /***
         * 不同类加载器，加载同一个类
         */
        ClassLoader cl = null;
        MyClassLoader myClassLoader = new MyClassLoader(cl);
        BrokeDelegateClassLoader brokeDelegateClassLoader = new BrokeDelegateClassLoader();

        Class<?> aClass1 = myClassLoader.loadClass("com.today.oop.Son");
        Class<?> aClass2 = brokeDelegateClassLoader.loadClass("com.today.oop.Son");
        System.out.println(aClass1.getClassLoader());
        System.out.println(aClass2.getClassLoader());
        System.out.println(aClass1.hashCode());
        System.out.println(aClass2.hashCode());
        System.out.println(aClass1 == aClass2);

        /***
         * 相同类型类加载器，加载同一个类
         */
        ClassLoader c2 = null;
        MyClassLoader myClassLoader3 = new MyClassLoader(c2);
        MyClassLoader myClassLoader4 = new MyClassLoader(c2);
        Class<?> aClass3 = myClassLoader3.loadClass("com.today.oop.Parent");
        Class<?> aClass4 = myClassLoader4.loadClass("com.today.oop.Parent");
        System.out.println(aClass3.getClassLoader());
        System.out.println(aClass4.getClassLoader());
        System.out.println(aClass4.hashCode());
        System.out.println(aClass2.hashCode());
        System.out.println(aClass3 == aClass4);

        /***
         * 769287236
         * 769287236
         * true
         * My ClassLoader
         * BrokeDelegateClassLoader
         * 2121055098
         * 2084435065
         * false
         * My ClassLoader
         * My ClassLoader
         * 806353501
         * 2084435065
         * false
         */
    }
}
