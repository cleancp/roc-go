package com.today.roc.go.spring.beans.beanFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年11月14日 12:20*
 * log.info()
 */
public class RocBeanFactory extends DefaultListableBeanFactory {

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return super.getBean(requiredType);
    }
}
