/**
 * Software License Declaration.
 * <p>
 * zhilingsd.com, Co,. Ltd.
 * Copyright © 2019 All Rights Reserved.
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
 * the third party without the agreement of wandaph. If Any problem cannot be solved in the
 * procedure of programming should be feedback to zhilingsd Co,. Ltd Inc in time, Thank you!
 */
package com.today.roc.go.websocket.ws;

import com.today.roc.go.websocket.configuration.JsonDecoder;
import com.today.roc.go.websocket.configuration.JsonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Session;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

//TODO 保存前端传过来的录音
//TODO 静音检测，自动start,stop识别语音流

/**
 * @author ZhangRong
 * @version Id: WebSocket, v 0.1 2020/3/7 12:02 ZhangRong Exp $$
 */
@Slf4j
@Component
@ServerEndpoint(value = "/nls/websocket/{traineeExamTaskId}/{session}/{connectedNums}/{sceneConfigType}", encoders = JsonEncoder.class, decoders = JsonDecoder.class)
public class NlsWebSocket {

    /**
     *  建立长连接
     * @param session 长连接session
     * @param examTaskId 考试任务id
     * @param userSession 用户session
     * @param connectedNums 连接次数
     * @param sceneConfigType 场景配置类型
     */
    @OnOpen
    public void onOpen(Session session,
                       @PathParam(value = "traineeExamTaskId") Long examTaskId,
                       @PathParam(value = "session") String userSession,
                       @PathParam(value = "connectedNums") Integer connectedNums,
                       @PathParam(value = "sceneConfigType") String sceneConfigType) {
        log.info("onOpen {}" ,System.currentTimeMillis() );
    }

    @OnClose
    public void onClose(@PathParam(value = "traineeExamTaskId") Long examTaskId, CloseReason closeReason) {
        log.info("onClose {}" ,System.currentTimeMillis() );
    }

    @OnError
    public void OnError(@PathParam(value = "traineeExamTaskId") Long examTaskId, Throwable throwable, Session session) {
        log.error("websocket长连接发生异常，examTaskId={}，异常信息为:{}", examTaskId, throwable);
    }


    /**
     * 收到客户端消息后调用的方法
     *
     * @param examTaskId
     * @param message
     */
    @OnMessage
    public void handlerBinaryMessage(@PathParam(value = "traineeExamTaskId") Long examTaskId, byte[] message) {
        log.info("OnMessage handlerBinaryMessage {}",System.currentTimeMillis());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param examTaskId
     * @param strMessage
     */
    @OnMessage
    public void handlerTextMessage(@PathParam(value = "traineeExamTaskId") Long examTaskId, String strMessage) {
        log.info("OnMessage handlerTextMessage {}",System.currentTimeMillis());
    }

}
