package com.today.rocactiviti;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RocActivitiApplication {

    public static void main(String[] args) {
        //todo 20210104
        /**
         * 1.数据库配置 oracle / mysql
         * 2.测试用例jar配置 加入junit (activiti_cfg.xml依赖jackson)
         * 3.加入Activity配置，启动roc-activiti项目成功
         */
        SpringApplication.run(RocActivitiApplication.class, args);
        log.info("===========启动成功==========");
    }

}
