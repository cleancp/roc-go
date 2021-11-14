package com.today.roc.go.websocket.configuration;

import com.alibaba.fastjson.JSONObject;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author: yuboliang
 * @date: 2020/3/24
 **/
public class JsonEncoder implements Encoder.Text<Object> {
    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public String encode(Object object) throws EncodeException {
        return JSONObject.toJSONString(object);
    }
}
