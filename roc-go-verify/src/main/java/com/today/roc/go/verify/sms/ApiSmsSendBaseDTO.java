package com.today.roc.go.verify.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * mujin-sms-platform-consumer
 *
 * @author liuzw
 * @date 2018-12-18
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiSmsSendBaseDTO implements Serializable {

    private static final long serialVersionUID = 7414772119300925219L;

    /**
     * 发送内容
     */
    private String sendContent;


    /**
     * 手机号
     */
    private String phone;

}
