package com.today.activity;

import com.today.roc.go.web.RocGoApplication;
import com.today.roc.go.web.activiti.LeaveController;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;

/**
 * Software License Declaration.
 * <p>
 * zhilingsd.com, Co,. Ltd.
 * Copyright © 2016 All Rights Reserved.
 * <p>
 * Copyright Notice
 * This documents is provided to zhilingsd contracting agent or authorized programmer only.
 * This source code is written and edited by zhilingsd Co,.Ltd Inc specially for financial
 * business contracting agent or authorized cooperative company, in order to help them to
 * install, programme or central control in certain project by themselves independently.
 * <p>
 * Disclaimer
 * If this source code is needed by the one neither contracting agent nor authorized programmer
 * during the use of the code, should contact to zhilingsd Co,. Ltd Inc, and get the confirmation
 * and agreement of three departments managers  - Research Department, Marketing Department and
 * Production Department.Otherwise zhilingsd will charge the fee according to the programme itself.
 * <p>
 * Any one,including contracting agent and authorized programmer,cannot share this code to
 * the third party without the agreement of zhilingsd. If Any problem cannot be solved in the
 * procedure of programming should be feedback to zhilingsd Co,. Ltd Inc in time, Thank you!
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年01月04日 17:13*
 * log.info()
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocGoApplication.class)
public class ActivityCreateMysqlTest {

    @Autowired
    LeaveController leaveController;
    /**
     * 使用activiti框架完成自动创建表（不设置配置文件）
     */
    @Test
    public void test01() {
        //创建一个流程成引擎对像
        ProcessEngineConfiguration conf = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
        //设置数据源
        conf.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        conf.setJdbcUrl("jdbc:mysql://localhost:3306/roc?allowMultiQueries=true&useUnicode=true&characterEncoding=utf8&serverTimeZone=UTC&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true");
        conf.setJdbcUsername("root");
        conf.setJdbcPassword("root");
        //设置自动创建表
        conf.setDatabaseSchemaUpdate("true");
        //在创建引擎对象的时候自动创建表
        ProcessEngine processEngine = conf.buildProcessEngine();
    }

    @Test
    public void testDeploy() throws FileNotFoundException {
        leaveController.deploy2();
    }
}
