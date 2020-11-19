package com.today.roc.go.verify.sms;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuzw
 * @date 2018-12-18
 **/

public class ApiSmsSendMarketingSmsDTO implements Serializable {

    private static final long serialVersionUID = 7414772119300925219L;

    /**
     * 发送内容
     */
    private String sendContent;

    /**
     * 手机号
     */
    private List<String> phones;

    private ApiSmsSendMarketingSmsDTO(Builder builder) {
        this.sendContent = builder.sendContent;
        this.phones = builder.phones;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        /**
         * 发送内容
         */
        private String sendContent;

        /**
         * 手机号
         */
        private List<String> phones;

        public Builder sendContent(String sendContent) {
            this.sendContent = sendContent;
            return this;
        }

        public Builder phones(List<String> phones) {
            this.phones = phones;
            return this;
        }

        public ApiSmsSendMarketingSmsDTO build() {
            return new ApiSmsSendMarketingSmsDTO(this);
        }
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }
}
