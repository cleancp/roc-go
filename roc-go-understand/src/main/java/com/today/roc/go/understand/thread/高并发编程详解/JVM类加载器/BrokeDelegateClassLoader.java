package com.today.roc.go.understand.thread.高并发编程详解.JVM类加载器;

import sun.misc.PerfCounter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月07日 11:37*
 * log.info()
 */
public class BrokeDelegateClassLoader extends MyClassLoader {

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                if (name.startsWith("java.") || name.startsWith("javax")) {
                    c = getSystemClassLoader().loadClass(name);
                } else {
                    c = super.findClass(name);
                    if (c == null) {
                        try {
                            if (getParent() != null) {
                                c = getParent().loadClass(name);
                            } else {
                                c = getSystemClassLoader().loadClass(name);
                            }
                        } catch (ClassNotFoundException e) {
                            // ClassNotFoundException thrown if class not found
                            // from the non-null parent class loader
                        }
                    }
                }
            }
            if (c == null){
                throw new ClassNotFoundException("The class "+name+" not found");
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    @Override
    public String toString() {
        return "BrokeDelegateClassLoader";
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        BrokeDelegateClassLoader brokeDelegateClassLoader = new BrokeDelegateClassLoader();
        Class<?> aClass = brokeDelegateClassLoader.loadClass("com.today.roc.go.understand.thread.高并发编程详解.JVM类加载器.HelloWorld");
        System.out.println(aClass.getClassLoader());
        Object instance = aClass.newInstance();
        System.out.println(instance);
        Method hello = aClass.getMethod("hello");
        Object invoke = hello.invoke(instance);
        System.out.println("Result：" + invoke);
    }

}
