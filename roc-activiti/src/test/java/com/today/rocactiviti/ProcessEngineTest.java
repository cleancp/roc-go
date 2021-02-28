package com.today.rocactiviti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;

import java.util.List;

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
 * @createTime 2021年01月06日 10:21*
 * log.info()
 */
@Slf4j
public class ProcessEngineTest {

    public static ProcessEngine buildProcessEngine() {
        //创建一个流程成引擎对像
        ProcessEngineConfiguration conf = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
        //设置数据源
        conf.setJdbcDriver("oracle.jdbc.driver.OracleDriver");
        conf.setJdbcUrl("jdbc:oracle:thin:@192.168.18.231:1521/helowin");
        conf.setJdbcUsername("asset");
        conf.setJdbcPassword("Asset@2020");
        //设置自动创建表
        conf.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE);
        //创建流程引擎
        ProcessEngine engine = conf.buildProcessEngine();
        return engine;
    }

    public static void main(String[] args) {
        bindUserWithGroup();
    }


    public static void bindUserWithGroup(){
        ProcessEngine processEngine = buildProcessEngine();
        /**得到身份服务组件*/
        IdentityService identityService = processEngine.getIdentityService();
        for (int i = 100; i < 110; i++) {
            creatUser(identityService, i+"", "angus", "cp1", "abc@l63.com", "123");
        }
        for (int i = 111; i < 115; i++) {
            createGroup(identityService, i+"", "角色ID"+i, "manage"+i);
        }
        identityService.createMembership(100+"",111+"");
        identityService.createMembership(101+"",111+"");
        identityService.createMembership(107+"",111+"");
        identityService.createMembership(108+"",111+"");
        identityService.createMembership(109+"",111+"");
        identityService.createMembership(102+"",112+"");
        identityService.createMembership(103+"",112+"");
        identityService.createMembership(104+"",112+"");
        identityService.createMembership(105+"",113+"");
        identityService.createMembership(106+"",114+"");
    }

    /**
     * 绑定流程定义与用户关系 用户权限
     * 内部不会根据关系做权限判断，只会返回对应数据
     */
    public static void bindUserWithDef() {
        ProcessEngine processEngine = buildProcessEngine();
        /**管理流程定义和流程部署*/
        RepositoryService repositoryService = processEngine.getRepositoryService();
        /**得到身份服务组件*/
        IdentityService identityService = processEngine.getIdentityService();
        for (int i = 100; i < 110; i++) {
            creatUser(identityService, i+"", "angus", "cp1", "abc@l63.com", "123");
        }
        for (int i = 111; i < 115; i++) {
            createGroup(identityService, 111+"", "角色ID"+i, "manage");
        }
        //部署流程文件
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("leave.bpmn").deploy();
        //查询流程定义实体
        ProcessDefinition def = repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
        //设置用户组与流程定义的关系（设置权限）
        repositoryService.addCandidateStarterUser(def.getId(), "user1");
        repositoryService.addCandidateStarterUser(def.getId(), "user2");
        repositoryService.addCandidateStarterGroup(def.getId(), "group1");
        repositoryService.addCandidateStarterGroup(def.getId(), "group2");
    }


    public static void listUserBindDef() {
        String defId = "process1:4:7504";
        String userId = "user1";
        ProcessEngine processEngine = buildProcessEngine();
        /**管理流程定义和流程部署*/
        RepositoryService repositoryService = processEngine.getRepositoryService();
        IdentityService identityService = processEngine.getIdentityService();

        //根据用户查询有权限的流程定义
        List<ProcessDefinition> userDefs = repositoryService.createProcessDefinitionQuery().startableByUser(userId).list();
        log.info("{} 有权限的流程定义：{}", userId, userDefs);

        //根据流程定义查询用户组数据
        List<Group> groups = identityService.createGroupQuery().potentialStarter(defId).list();
        log.info("{} 的流程定义用户组信息：{}", defId, groups);

        //根据流程定义查询用户数据
        List<User> users = identityService.createUserQuery().potentialStarter(defId).list();
        log.info("{} 的流程定义用户信息：{}", defId, users);

        //根据流程定义查询全部的 IdentityLink (ACT_RU_IDENTITYLINK 表〉 数据
        List<IdentityLink> links = repositoryService.getIdentityLinksForProcessDefinition(defId);
        log.info("{} 的流程定义信息：{}", defId, links);
    }


    /**
     * 流程任务处理
     */
    public static void TaskTest(){
        ProcessEngine processEngine = buildProcessEngine();
        /**管理流程定义和流程部署*/
        RepositoryService repositoryService = processEngine.getRepositoryService();
        /**管理流程运行时的数据*/
        TaskService taskService = processEngine.getTaskService();
        saveTask(taskService,"task1");

        taskService.deleteTask("task1");
        //级联删除
        taskService.deleteTask("task1",true);
    }

    //保存Task
    public static  void  saveTask(TaskService taskService,String id){
        Task task = taskService.newTask(id);
        taskService.saveTask(task);
    }

    //创建用户
    public static void creatUser(IdentityService identityService, String id,
                                 String first, String last, String email, String passwd) {
        //使用 newUser 方法创建 User 实例
        User user = identityService.newUser(id);
        //设置用户的各个属性
        user.setFirstName(first);
        user.setLastName(last);
        user.setEmail(email);
        user.setPassword(passwd);
        //使用saveUser方法保存用户
        identityService.saveUser(user);
    }

    //创建用户组
    public static void createGroup(IdentityService identityService, String id, String name, String type) {
        //调用 newGroup 方法创建 Group 实例
        Group group = identityService.newGroup(id);
        group.setName(name);
        group.setType(type);
        identityService.saveGroup(group);
    }
}
