package com.today.roc.go.verify.sms;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * mujin-sms-platform-consumer
 *
 * @author liuzw
 * @date 2018-12-18
 **/
@Data
@Builder
public class ApiSmsSendRemindDTO implements Serializable {

    private static final long serialVersionUID = 7414772119300925219L;


    /**
     * 签名
     */
    private String signContent;

    /**
     * 发送内容和手机号
     */
    private List<ApiSmsSendBaseDTO> details;


}
