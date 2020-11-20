package com.today.roc.go.verify.sms;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liuzw
 */
@Data
@Builder
public class ApiCommonOpenDTO implements Serializable {

    private static final long serialVersionUID = 5774011786115656276L;

    /**
     * 准入key
     */
    private String accessKey;

    /**
     * 唯一的随机字符串
     */
    private String nonce;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 签名
     */
    private String sign;

    /**
     * 参数对象
     */
    private Object data;

    /**
     * 商户api账号id
     */
    private Long accountId;

}
