package com.today.roc.go.spring.beans.factory.xml;

import com.today.roc.go.spring.beans.factorybean.Car;
import com.today.roc.go.spring.beans.factorybean.CarFactoryBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月31日 00:21*
 * log.info()
 */
public class XmlBeanFactoryMain {
    public static void main(String[] args) {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
        //不能解析@Value注解
        CarFactoryBean carFactoryBean = (CarFactoryBean) bf.getBean("&carFactoryBean");
        System.out.println(carFactoryBean);
    }
}
