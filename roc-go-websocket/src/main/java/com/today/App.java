package com.today;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.zhilingsd.*"}, exclude = DataSourceAutoConfiguration.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        log.info("==========启动成功==========");
    }
}
