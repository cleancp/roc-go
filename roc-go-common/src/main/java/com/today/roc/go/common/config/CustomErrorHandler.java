package com.today.roc.go.common.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @program zlsd
 * @description: 自定义异常处理
 * @author: LiuHang
 * @create: 2020/07/24 14:08
 * @Copyright: 2018 www.mujinkeji.com Inc. All rights reserved.
 */

public class CustomErrorHandler extends DefaultResponseErrorHandler {
    public CustomErrorHandler() {
        super();
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return super.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }

    @Override
    protected HttpStatus getHttpStatusCode(ClientHttpResponse response) throws IOException {
        return super.getHttpStatusCode(response);
    }

    @Override
    protected boolean hasError(HttpStatus statusCode) {
        return super.hasError(statusCode);
    }

    @Override
    protected Charset getCharset(ClientHttpResponse response) {
        return super.getCharset(response);
    }

    @Override
    protected byte[] getResponseBody(ClientHttpResponse response) {
        return super.getResponseBody(response);
    }
}
