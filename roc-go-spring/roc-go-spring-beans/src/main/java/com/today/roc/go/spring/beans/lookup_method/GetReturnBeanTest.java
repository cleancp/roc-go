package com.today.roc.go.spring.beans.lookup_method;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月26日 22:19*
 * log.info()
 */
@Component
public abstract class GetReturnBeanTest {

    public void showMe() {
        this.getBean().showMe();
    }

    @Lookup("user")
    public abstract User getBean();

}
