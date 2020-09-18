package com.today.roc.go.common.config;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @program: 智灵时代广州研发中心
 * @description:
 * @author: 吞星(yangguojun)
 * @create: 2020-03-30 14:33
 **/
@Configuration
@Component
@ConditionalOnClass(value = {RestTemplate.class, CloseableHttpClient.class})
@Data
@Slf4j
public class HttpClientConfig {

    @Autowired
    private HttpClientPoolProperties httpClientPoolProperties;


    /**
     * 功能描述 创建HTTP客户端工厂
     *
     * @param
     * @return org.springframework.http.client.ClientHttpRequestFactory
     * @auther 吞星（yangguojun）
     * @date 2020/3/30-15:00
     */
    @Bean(name = "clientHttpRequestFactory")
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        if (httpClientPoolProperties.getMaxTotalConnect() <= 0) {
            throw new IllegalArgumentException("最大连接数不合法: " + httpClientPoolProperties.getMaxTotalConnect());
        }
        if (httpClientPoolProperties.getMaxConnectPerRoute() <= 0) {
            throw new IllegalArgumentException("同一个主机最大并发数不合法" + httpClientPoolProperties.getMaxConnectPerRoute());
        }
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
        // 连接超时时间
        clientHttpRequestFactory.setConnectTimeout(httpClientPoolProperties.getConnectTimeout());
        // 处理超时时间
        clientHttpRequestFactory.setReadTimeout(httpClientPoolProperties.getReadTimeout());
        // 从连接池获取请求连接的超时时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        clientHttpRequestFactory.setConnectionRequestTimeout(httpClientPoolProperties.getConnectionRequestTimout());
        return clientHttpRequestFactory;
    }

    /**
     * 功能描述:初始化RestTemplate,并加入spring的Bean工厂，由spring统一管理
     *
     * @param factory
     * @return org.springframework.web.client.RestTemplate
     * @auther 吞星（yangguojun）
     * @date 2020/7/14-15:44
     */
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return createRestTemplate(factory);
    }


    /**
     * 功能描述 :初始化支持异步的RestTemplate,并加入spring的Bean工厂，由spring统一管理,如果你用不到异步，则无须创建该对象
     * @param restTemplate
     * @return org.springframework.web.client.AsyncRestTemplate
     * @auther 吞星（yangguojun）
     * @date 2020/7/14-15:45
     */
    @Bean(name = "asyncRestTemplate")
    @ConditionalOnMissingBean(AsyncRestTemplate.class)
    public AsyncRestTemplate asyncRestTemplate(RestTemplate restTemplate) {

        final Netty4ClientHttpRequestFactory factory = new Netty4ClientHttpRequestFactory();
        factory.setConnectTimeout(2 * 1000);
        factory.setReadTimeout(3 * 1000);
        return new AsyncRestTemplate(factory, restTemplate);
    }


    /**
     * 功能描述  配置httpClient
     *
     * @param
     * @return org.apache.http.client.HttpClient
     * @auther 吞星（yangguojun）
     * @date 2020/3/30-16:45
     */
    @Bean
    public HttpClient httpClient() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        try {
            //设置信任ssl访问
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1) -> true).build();

            httpClientBuilder.setSSLContext(sslContext);
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    // 注册http和https请求
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslConnectionSocketFactory).build();
            //使用Httpclient连接池的方式配置(推荐)，同时支持netty，okHttp以及其他http框架
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // 最大连接数
            poolingHttpClientConnectionManager.setMaxTotal(httpClientPoolProperties.getMaxTotalConnect());
            // 同路由并发数
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(httpClientPoolProperties.getMaxConnectPerRoute());
            //配置连接池
            httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
            // 重试次数
            httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(httpClientPoolProperties.getRetryTimes(), true));
            //设置长连接保持策略
            httpClientBuilder.setKeepAliveStrategy(connectionKeepAliveStrategy());
            return httpClientBuilder.build();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            log.error("初始化HTTP连接池出错", e);
        }
        return null;
    }


    /**
     * 功能描述  配置长连接保持策略
     *
     * @param
     * @return org.apache.http.conn.ConnectionKeepAliveStrategy
     * @auther 吞星（yangguojun）
     * @date 2020/3/30-16:45
     */
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return (response, context) -> {
            // 获得http请求头(header)的keep-alive
            HeaderElementIterator it = new BasicHeaderElementIterator(
                    response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                log.info("HeaderElement:{}", JSON.toJSONString(he));
                String param = he.getName();
                String value = he.getValue();
                if (value != null && "timeout".equalsIgnoreCase(param)) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (NumberFormatException ignore) {
                        log.error("解析长连接过期时间异常", ignore);
                    }
                }
            }
            HttpHost target = (HttpHost) context.getAttribute(
                    HttpClientContext.HTTP_TARGET_HOST);
            //如果请求目标地址,单独配置了长连接保持时间,使用该配置
            Optional<Map.Entry<String, Integer>> any = Optional.ofNullable(httpClientPoolProperties.getKeepAliveTargetHost()).orElseGet(HashMap::new)
                    .entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase(target.getHostName())).findAny();
            //否则使用默认长连接保持时间
            return any.map(en -> en.getValue() * 1000L).orElse(httpClientPoolProperties.getKeepAliveTime() * 1000L);
        };
    }


    /**
     * 功能描述
     *
     * @param factory
     * @return org.springframework.web.client.RestTemplate
     * @auther 吞星（yangguojun）
     * @date 2020/3/30-16:46
     */
    private RestTemplate createRestTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        //我们采用RestTemplate内部的MessageConverter
        //重新设置StringHttpMessageConverter字符集，解决中文乱码问题
        modifyDefaultCharset(restTemplate);
        //设置错误处理器
        restTemplate.setErrorHandler(new CustomErrorHandler());
        return restTemplate;
    }

    /**
     * 修改默认的字符集类型为utf-8
     *
     * @param restTemplate
     */
    private void modifyDefaultCharset(RestTemplate restTemplate) {
        Charset defaultCharset = Charset.forName(httpClientPoolProperties.getCharset());
        restTemplate.getMessageConverters().clear();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(defaultCharset));
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }
}


