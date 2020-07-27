package com.today.roc.go.spring.beans.factorybean;

import com.today.roc.go.spring.beans.lookup_method.GetReturnBeanTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月27日 10:33*
 * log.info()
 */
public class FactoryBeanMain {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Car car = (Car) ac.getBean("carFactoryBean");
        CarFactoryBean carFactoryBean = (CarFactoryBean) ac.getBean("&carFactoryBean");
        System.out.println(car);
        System.out.println(carFactoryBean);
    }
}
