package com.today.roc.go.spring.mybatis;

import com.today.roc.go.spring.mybatis.model.RocUser;
import com.today.roc.go.spring.mybatis.service.RocUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年09月20日 10:33*
 * log.info()
 */
public class TrancationMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-transaction.xml");
        RocUserService rocUserService = (RocUserService)context.getBean("rocUserServiceImpl");
        RocUser rocUser = new RocUser();
        rocUser.setBirthday(new Date());
        rocUser.setName("王五3");
        rocUser.setNamePinyin("ww2");
        rocUserService.save(rocUser);
    }
}
