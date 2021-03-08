package com.today.roc.go.understand.thread.高并发编程详解.JVM类加载器;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月06日 23:57*
 * log.info()
 */
public class TestClassLoader {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        //扩展类加载器
        ClassLoader cl = TestClassLoader.class.getClassLoader().getParent();
        MyClassLoader classLoader = new MyClassLoader(cl); //父类传扩展类或根加载器 会跳过AppClassLoader加载
//        ClassLoader cl = null;
//        MyClassLoader classLoader = new MyClassLoader(); //父类传null 会走向根加载器加载
        Class<?> aClass = classLoader.loadClass("com.today.roc.go.understand.thread.高并发编程详解.JVM类加载器.HelloWorld");
        System.out.println(aClass.getClassLoader());
        Object instance = aClass.newInstance();
        System.out.println(instance);
        Method hello = aClass.getMethod("hello");
        Object invoke = hello.invoke(instance);
        System.out.println("Result：" + invoke);
    }
}
