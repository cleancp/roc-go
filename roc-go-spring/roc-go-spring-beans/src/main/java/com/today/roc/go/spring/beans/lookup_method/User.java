package com.today.roc.go.spring.beans.lookup_method;

import org.springframework.stereotype.Component;

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
public class User {

    public void showMe(){
        System.out.println("i am user");
    }

}
