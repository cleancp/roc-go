package com.today.roc.go.spring.beans.factory.xml;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年08月02日 00:15*
 * log.info()
 */
public class RocXmlApplicationContextMain {
    public static void main(String[] args) {
        RocXmlWebApplicationContext ac = new RocXmlWebApplicationContext("beanFactoryTest.xml");
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        ac.close();
    }
}
