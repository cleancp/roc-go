package com.today.rocactiviti;

import org.activiti.engine.repository.ProcessDefinition;

import java.util.HashMap;
import java.util.Map;

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
 * @createTime 2021年01月12日 19:58*
 * log.info()
 */
public class StrategyTest {

//    //启动报销流程
//    public Processinstance startExpenseAccount(ExpenseAccountForm expenseAccountForm) {
//        expenseAccountForm.setTitle(expenseAccountForm.getUserName()报销申请”）;
//        expenseAccountForm.setBus nessType （”报销申请”）；
//        //查找流程定义
//        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
//                .processDefinitionKey(” ExpenseAccount”).singleResult();
//        // 初始化流程参数
//        Map<String, Object> vars = new HashMap<String, Object>();
//        vars.put(” arg”, expenseAccountForm);
//        //启动流程
//        Processinstance pi = this.runtimeService.startProcessinstanceByKey(pd
//                .getKey());
//        Task task = this.taskService.createTaskQuery()
//                .processinstanceid(pi.getid()).singleResult();
//        //完成任务
//        taskService.complete(task.getid(), vars);
//        //保存到业务系统的数据库中
//        saveExpenseAccount(expenseAccountForm, pi.getid());
//        return pi;
//    }

}
