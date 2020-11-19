package com.today.roc.go.verify.sms;

import java.io.Serializable;

/**
 * 发送催收短信
 *
 * @author liuzw
 * @date 2018-12-18
 **/
public class ApiSmsSendCollectionDTO implements Serializable {

    private static final long serialVersionUID = 7414772119300925219L;

    /**
     * 发送内容
     */
    private String sendContent;

    /**
     * 手机号
     */
    private String phone;

    private ApiSmsSendCollectionDTO(Builder builder) {
        this.sendContent = builder.sendContent;
        this.phone = builder.phone;
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
        private String phone;


        public Builder sendContent(String sendContent) {
            this.sendContent = sendContent;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public ApiSmsSendCollectionDTO build() {
            return new ApiSmsSendCollectionDTO(this);
        }

    }


    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
