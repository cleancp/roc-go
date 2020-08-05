package com.today.roc.go.spring.beans.factory.xml;

import com.today.roc.go.spring.beans.bean.processor.RocBeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年08月02日 00:06*
 * log.info()
 */
@Component
public class RocXmlWebApplicationContext extends ClassPathXmlApplicationContext {

    public RocXmlWebApplicationContext(){
        super();
    }

    public RocXmlWebApplicationContext(String configLocation) {
        super(configLocation);
    }

    @Override
    protected void initBeanDefinitionReader(XmlBeanDefinitionReader beanDefinitionReader) {
        beanDefinitionReader.setDocumentReaderClass(RocBeanDefinitionDocumentReader.class);
        super.initBeanDefinitionReader(beanDefinitionReader);
    }

    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        super.postProcessBeanFactory(beanFactory);
        System.out.println("对BeanFactory做自定义处理");
    }
}
