package com.today.roc.go.spring.beans.processor.beanfactory;

import com.google.common.collect.Sets;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import java.util.Set;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年08月09日 14:51*
 * log.info()
 */
@Component("sensitiveBFPP")
public class SensitiveWorkBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {

    private Set<String> sensitiveWords = Sets.newHashSet("fuck", "shit");

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println(this.getClass().getSimpleName()+"：敏感词处理 Ordered 999");
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition bd = configurableListableBeanFactory.getBeanDefinition(beanDefinitionName);
            StringValueResolver valueResolver = new StringValueResolver() {
                @Override
                public String resolveStringValue(String s) {
                    return isSensitiveWord(s) ? "****" : s;
                }
            };
            BeanDefinitionVisitor visitor = new BeanDefinitionVisitor(valueResolver);
            visitor.visitBeanDefinition(bd);
        }
    }

    public boolean isSensitiveWord(Object val) {
        String lowerCase = val.toString().toLowerCase();
        if (sensitiveWords.contains(lowerCase)) {
            return true;
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 999;
    }
}
