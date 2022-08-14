package com.today.asr;

import com.alibaba.fastjson.JSONObject;
import com.today.roc.go.web.RocGoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

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
 * @createTime 2020年07月23日 17:42*
 * log.info()
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocGoApplication.class)
public class AsrTest {

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void test(){
        SinoOfflineAsrStatusBo bo = new SinoOfflineAsrStatusBo();
        //202007211410268528f2fd735f000417
        bo.setTask_no("202007141947209f07bd011d84000244");
        String url = "http://192.168.14.203:9999/getStatus";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setCacheControl("no-store");
        requestHeaders.setDate(new Date().getTime());
        requestHeaders.setConnection("keep-alive");
        requestHeaders.set("Server","TransHttp/v1.0 libevhtp/1.2.11 libevent/2.0.21-stable");
        requestHeaders.setContentLength(207L);
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(JSONObject.toJSONString(bo), requestHeaders);
        ResponseEntity<SinoOfflineAsrStatusResponse> responseEntity = restTemplate.postForEntity(url, requestEntity, SinoOfflineAsrStatusResponse.class);
        System.out.println(responseEntity);
    }

}
