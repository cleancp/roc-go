package com.today.roc.go.spring.beans.lookup_method;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import sun.java2d.DisposerRecord;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月26日 22:17*
 * log.info()
 */
@Component
public class Teacher extends User implements DisposableBean {

    @Override
    public void showMe() {
        System.out.println("i am teacher");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(this.getClass().getSimpleName()+"实现 DisposableBean destory");
    }
}
