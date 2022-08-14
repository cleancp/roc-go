

### 反射

    Class<?> aClass = Class.forName("JsonBO");
    
获取字段
    
    Field[] declaredFields = aClass.getDeclaredFields();//获取声明的字段
    declaredField.setAccessible(true);//设置允许访问修改    
    declaredField.get(jsonBO); //获取jsonBO对象的字段值
    declaredField.set(jsonBO,1234L);//给字段设置值
    Annotation[] annotations = declaredField.getAnnotations();//获取字段所有注解
    Expose annotation1 = declaredField.getAnnotation(Expose.class);//获取字段注解对象
    Method[] declaredMethods = aClass.getDeclaredMethods();//获取类的所有方法
    Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();//获取类的构造
    Annotation[] annotations = aClass.getAnnotations();//获取类的注解
    
    