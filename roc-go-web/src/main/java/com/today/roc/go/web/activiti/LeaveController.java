package com.today.roc.go.web.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

@Controller
@Slf4j
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private ProcessEngine processEngine;

    //部署流程资源【第一种方式：classpath】
    @RequestMapping("/deploy1")
    public void deploy1() {
        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .name("请假申请审核流程")//声明流程的名称
                .addClasspathResource("processes/leave.bpmn")//加载资源文件，一次只能加载一个文件
                .deploy();//完成部署
        System.out.println("部署ID：" + deployment.getId());//1
        System.out.println("部署时间：" + deployment.getDeploymentTime());

    }

    //部署流程资源【第二种方式：InputStream】
    @RequestMapping("/deploy2")
    public void deploy2() throws FileNotFoundException {
        //获取资源相对路径
        String bpmnPath = "processes/leave.bpmn";
        //读取资源作为一个输入流
        FileInputStream bpmnfileInputStream = new FileInputStream(bpmnPath);
        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addInputStream("leave.bpmn", bpmnfileInputStream)
                .deploy();//完成部署
        System.out.println("部署ID：" + deployment.getId());//1
        System.out.println("部署时间：" + deployment.getDeploymentTime());
    }

    //部署流程资源【第三种方式：InputStream】
    @RequestMapping("/deploy3")
    public void deploy3() throws FileNotFoundException {
        //字符串
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><definitions>...</definitions>";

        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addString("leave.bpmn", text)
                .deploy();//完成部署
        System.out.println("部署ID：" + deployment.getId());//1
        System.out.println("部署时间：" + deployment.getDeploymentTime());

    }

    //部署流程资源【第四种方式：zip/bar格式压缩包方式】
    @RequestMapping("/deploy4")
    public void deploy4() throws FileNotFoundException {
        //从classpath路径下读取资源文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("processes/leave.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addZipInputStream(zipInputStream)//使用zip方式部署，将leave.bpmn和leave.png压缩成zip格式的文件
                .deploy();//完成部署
        System.out.println("部署ID：" + deployment.getId());//1
        System.out.println("部署时间：" + deployment.getDeploymentTime());

    }


}


