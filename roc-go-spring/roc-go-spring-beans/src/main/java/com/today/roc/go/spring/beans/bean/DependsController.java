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
 * @createTime 2020年11月09日 17:24*
 * log.info()
 */
@Component
public class DependsController {

    private DependsService dependsService;

    public DependsController() {
    }

    @Autowired
    public DependsController(DependsService dependsService) {
        this.dependsService = dependsService;
    }

    public void test(){
        System.out.println("DependsController");
    }

}
