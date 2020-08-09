package com.today.roc.go.spring.aop.test;

import org.springframework.stereotype.Component;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年08月09日 22:22*
 * log.info()
 */
@Component
public class TestBean {

    public void test() {
        System.out.println("test");
    }
}
