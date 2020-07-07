package com.today.roc.go.common.utils.reflect;

import com.today.roc.go.common.bo.JsonBO;
import com.today.roc.go.common.utils.json.JsonUtils;

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
        Class<? extends JsonBO> aClass = jsonBO.getClass();

    }


    public static void testForName() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("com.today.roc.go.common.bo.JsonBO");
        Object o = aClass.newInstance();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method  declaredMethod: declaredMethods) {
            System.out.println(declaredMethod);
        }
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }


    }

}
