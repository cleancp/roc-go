package com.today.roc.go.spring.beans.factory.xml;

import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.w3c.dom.Element;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月26日 15:22*
 * log.info()
 */
public class RocBeanDefinitionDocumentReader extends DefaultBeanDefinitionDocumentReader {

    //解析前处理
    @Override
    protected void preProcessXml(Element root) {
        super.preProcessXml(root);
        System.out.println(root);
    }

    //解析前处理
    @Override
    protected void postProcessXml(Element root) {
        super.postProcessXml(root);
        System.out.println(root);
    }
}
