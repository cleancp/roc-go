package com.today.roc.go.common.utils.reflect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.today.roc.go.common.bo.JsonBO;
import com.today.roc.go.common.utils.json.JsonUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月05日 10:17*
 * log.info()
 */
public class ReflectUtils {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        JsonBO jsonBO = JsonUtils.buildObject(JsonBO.class, 10);
        testForName();
    }


    public static void testValue(JsonBO jsonBO) {
        Class<? extends JsonBO> aClass = jsonBO.getClass();
    }


    public static void testForName() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("com.today.roc.go.common.bo.JsonBO");
        //Object o = aClass.newInstance();
        JsonBO jsonBO = JsonUtils.buildObject(JsonBO.class, 10);
        System.out.println("字段-------------------");
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
            declaredField.setAccessible(true);
            System.out.println(declaredField.get(jsonBO));
            if (declaredField.getName().endsWith("id")){
                declaredField.set(jsonBO,1234L);
            }
            System.out.println(declaredField.get(jsonBO));
            //获取字段注解值
            Annotation[] annotations = declaredField.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Expose) {
                    Expose annotation1 = declaredField.getAnnotation(Expose.class);
                    System.out.println(annotation1);
                }
                if (annotation instanceof SerializedName) {
                    SerializedName annotation1 = declaredField.getAnnotation(SerializedName.class);
                    System.out.println(annotation1);
                }
            }
        }
        System.out.println("方法-------------------");
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        System.out.println("构造-------------------");
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }
        System.out.println("注解");
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}
