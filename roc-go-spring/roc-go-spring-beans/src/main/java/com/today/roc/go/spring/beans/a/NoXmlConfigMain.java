package com.today.roc.go.spring.beans.a;

import com.today.roc.go.spring.beans.config.AopConfig;
import com.today.roc.go.spring.beans.lookup_method.Teacher;
import com.today.roc.go.spring.beans.lookup_method.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年10月25日 17:53*
 * log.info()
 */
public class NoXmlConfigMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(AopConfig.class);
        app.start();
        Teacher bean = app.getBean(Teacher.class);
        bean.showMe();

        User bean1 = (User)app.getBean("user");
        bean1.showMe();
        //报错:NoUniqueBeanDefinitionException
        //No qualifying bean of type 'com.today.roc.go.spring.beans.lookup_method.User' available: expected single matching bean but found 2: teacher,user
        User bean2 = app.getBean(User.class);
        bean2.showMe();
    }
}
