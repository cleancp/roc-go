package com.today.roc.go.spring.beans.bean;

import org.springframework.stereotype.Component;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年10月25日 17:59*
 * log.info()
 */
@Component
public class NoXmlConfigBean {

    public void showXmlConfig(){
        System.out.println("showXmlConfig");
    }

}
