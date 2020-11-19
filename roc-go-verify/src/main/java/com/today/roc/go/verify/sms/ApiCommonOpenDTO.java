package com.today.roc.go.verify.sms;

import java.io.Serializable;

/**
 * @author liuzw
 */
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

    private ApiCommonOpenDTO(Builder builder) {
        this.accessKey = builder.accessKey;
        this.nonce = builder.nonce;
        this.timestamp = builder.timestamp;
        this.sign = builder.sign;
        this.data = builder.data;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
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

        public Builder accessKey(String accessKey) {
            this.accessKey = accessKey;
            return this;
        }

        public Builder nonce(String nonce) {
            this.nonce = nonce;
            return this;
        }

        public Builder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder sign(String sign) {
            this.sign = sign;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }
        public ApiCommonOpenDTO build() {
            return new ApiCommonOpenDTO(this);
        }
    }



    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
