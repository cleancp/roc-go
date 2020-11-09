package com.today.roc.go.spring.beans.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年11月09日 17:25*
 * log.info()
 */
@Component
public class DependsService {

    @Autowired
    DependsController dependsController;

    public static void main(String[] args) {
        System.out.println("循环依赖");
    }

    public void test() {
        System.out.println("DependsService");
    }

}
