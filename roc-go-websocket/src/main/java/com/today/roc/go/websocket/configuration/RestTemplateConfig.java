package com.today.roc.go.websocket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author yuboliang
 * @date 2020-3-6
 */
@Configuration
public class RestTemplateConfig {

    @Bean("httpsRestTemplate")
    public RestTemplate httpsRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
        restTemplate.setInterceptors(Collections.singletonList(new LoggingClientHttpRequestInterceptor()));

        return restTemplate;
    }

    @Bean("restTemplate")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new LoggingClientHttpRequestInterceptor()));

        return restTemplate;
    }
}
