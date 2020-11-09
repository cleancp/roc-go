package com.today.roc.go.spring.beans.processor;

import com.today.roc.go.spring.beans.bean.DependsController;
import com.today.roc.go.spring.beans.bean.DependsService;
import com.today.roc.go.spring.beans.bean.SensitiveWordBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年08月09日 15:04*
 * log.info()
 */
public class ProcessMain {

    public static void main(String[] args) {
        testBeanFactoryProcess();
    }

    public static void testBeanFactoryProcess(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("processTest.xml");
//        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
//        System.out.println("===============================");
//        //
//        SensitiveWordBean sensitiveWordBean = (SensitiveWordBean) ac.getBean("sensitiveWordBean");
//        System.out.println(sensitiveWordBean);

        DependsController dependsController = (DependsController) ac.getBean("dependsController");
        dependsController.test();

        DependsService dependsService = (DependsService) ac.getBean("dependsService");
        dependsService.test();
    }
}
