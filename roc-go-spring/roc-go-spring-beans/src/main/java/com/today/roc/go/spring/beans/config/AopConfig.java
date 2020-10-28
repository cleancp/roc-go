package com.today.roc.go.spring.beans.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年10月25日 17:53*
 * log.info()
 */
@Configuration
@ComponentScan(basePackages = {"com.today.roc.go.spring.beans.*"})
public class AopConfig {
}
