package com.today.roc.go.spring.beans.processor.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年08月06日 23:27*
 * log.info()
 */
@Component
public class RocBeanFactoryPostProcessor implements BeanFactoryPostProcessor , Ordered {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println(this.getClass().getSimpleName()+" 执行处理 Ordered 0 ");
    }

    /**
     * 值越小,优先级越大
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
