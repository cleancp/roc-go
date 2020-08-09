package com.today.roc.go.spring.beans.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年08月09日 17:17*
 * log.info()
 */
public class ListenerMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beanFactoryTest.xml");
        TestEvent event = new TestEvent("hello","msg");
        context.publishEvent(event);
    }
}
