package com.today.designPatterns.单例模式;

import javax.naming.NamingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * description：
 * author：roc.zou
 * 2021/6/26 1:07 下午
 */
public class BrokeSingletonTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NamingException, IOException, ClassNotFoundException {
        //reflectBrokeSingleton();
        //serializeBrokeSingleton();
        //枚举为什么不会又问题
        //不能反射枚举  java.lang.IllegalArgumentException: Cannot reflectively create enum objects
        //reflectCantBrokeSingletonEnum();
        // 序列化 相等
        serializeCantBrokeSingletonEnum();

    }

    private static void serializeCantBrokeSingletonEnum() throws NamingException, IOException, ClassNotFoundException {
        // INSTANCE will be initialized immediately
        SingletonEnum instance = SingletonEnum.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton_file"));
        // 序列化【写】操作
        oos.writeObject(instance);
        File file = new File("singleton_file");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        // 反序列化【读】操作
        SingletonEnum newInstance = (SingletonEnum) ois.readObject();
        System.out.println("instance:"+instance);
        System.out.println("newInstance:"+newInstance);
        System.out.println(instance == newInstance);
    }

    private static void reflectCantBrokeSingletonEnum() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NamingException {
        // NoSuchMethodException: com.today.roc.go.understand.designPatterns.单例模式.SingletonEnum.<init>() 没有init方法
        // javap -c SingleEnum
        // 7: invokespecial #14                 // Method "<init>":(Ljava/lang/String;I)V  String int
        // java.lang.IllegalArgumentException: Cannot reflectively create enum objects
        Class<SingletonEnum> aClass = SingletonEnum.class;
        Constructor<SingletonEnum> declaredConstructor = aClass.getDeclaredConstructor(String.class,int.class);
        //设置可以访问私有方法
        declaredConstructor.setAccessible(true);
        SingletonEnum singleton = declaredConstructor.newInstance("",0);
        System.out.println("instance:"+singleton);
        SingletonEnum instance = SingletonEnum.getInstance();
        System.out.println("newInstance:"+instance);
    }

    private static void serializeBrokeSingleton() throws NamingException, IOException, ClassNotFoundException {
        DoubleCheckVolatileSingleton instance = DoubleCheckVolatileSingleton.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton_file"));
        // 序列化【写】操作
        oos.writeObject(instance);
        File file = new File("singleton_file");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        // 反序列化【读】操作
        DoubleCheckVolatileSingleton newInstance = (DoubleCheckVolatileSingleton) ois.readObject();
        System.out.println("instance"+instance);
        System.out.println("newInstance"+newInstance);
        System.out.println(instance == newInstance);
    }

    private static void reflectBrokeSingleton() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NamingException {
        Class<DoubleCheckVolatileSingleton> aClass = DoubleCheckVolatileSingleton.class;
        Constructor<DoubleCheckVolatileSingleton> declaredConstructor = aClass.getDeclaredConstructor();
        //设置可以访问私有方法
        declaredConstructor.setAccessible(true);
        DoubleCheckVolatileSingleton singleton = declaredConstructor.newInstance();
        System.out.println(singleton);
        DoubleCheckVolatileSingleton instance = DoubleCheckVolatileSingleton.getInstance();
        System.out.println(instance);
    }

}
