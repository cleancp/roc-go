package com.today.roc.go.verify.sms;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author liuzw
 */
public class SendSms {

    /**
     * 发送营销短信接口(地址请写接口文档的地址)
     */
    private static final String SEND_MARKETING_URL = "https://www.smsplatform.com/api/apiSms/sendMarketingSms";

    /**
     * 发送催收短信接口(地址请写接口文档的地址)
     */
    private static final String SEND_COLLECTION_URL = "https://www.smsplatform.com/api/apiSms/sendCollectionSms";

    /**
     * 获取短信发送结果(地址请写接口文档的地址)
     */
    private static final String SEND_RESULT_URL = "https://www.smsplatform.com/api/apiSms/getSmsSendResult";


    public static void main(String[] args) {
        //发送营销短信
        sendMarketingSms();
        //发送催收短信
//        sendCollection();
        //获取短信发送结果
        getSmsSendResult();
    }


    /**
     * 发送营销短信
     */
    private static void sendMarketingSms() {
        //封装参数
        ApiCommonOpenDTO build = build();
        //手机号
        List<String> phones = new ArrayList<>();
        phones.add("17603086701");
        //对应接口文档中的 data 参数
        ApiSmsSendMarketingSmsDTO data = ApiSmsSendMarketingSmsDTO.builder()
                .sendContent("测试测试")
                .phones(phones)
                .build();
        build.setData(data);
        //发送请求
        doPost(build, SEND_MARKETING_URL);
    }


    /**
     * 发送催收短信
     */
    private static void sendCollection() {
        //封装参数
        ApiCommonOpenDTO build = build();
        List<ApiSmsSendCollectionDTO> data = new ArrayList<>();
        data.add(ApiSmsSendCollectionDTO.builder()
                .phone("13788888888")
                .sendContent("测试测试")
                .build());
        build.setData(data);
        //发送请求
        doPost(build, SEND_COLLECTION_URL);
    }


    /**
     * 获取短信发送结果
     */
    private static void getSmsSendResult() {
        //封装参数
        ApiCommonOpenDTO build = build();
        build.setData("发送短信之后返回的值");
        //发送请求
        doPost(build, SEND_RESULT_URL);
    }


    /**
     * 封装基本参数
     *
     * @return ApiCommonOpenDTO
     */
    private static ApiCommonOpenDTO build() {
        String accessKey = "liu1235";
        String secretKey = "liu1235 ";
        //随机串
        String nonce = UUID.randomUUID().toString();
        //时间戳
        Long timestamp = System.currentTimeMillis();
        //计算签名
        String sign = Md5Utils.md5(secretKey + nonce + timestamp);
        //接口参数
        return ApiCommonOpenDTO.builder()
                .accessKey(accessKey)
                .nonce(nonce)
                .timestamp(timestamp)
                .sign(sign)
                .build();
    }


    private static void doPost(ApiCommonOpenDTO bean, String baseUrl) {
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            // 创建连接
            URL url = new URL(baseUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(sc.getSocketFactory());
            connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            // POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(JsonUtil.toJson(bean).getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuilder sb = new StringBuilder();
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), StandardCharsets.UTF_8);
                sb.append(lines);
            }
            //打印返回的结果
            System.out.println(sb.toString());
            reader.close();
            // 断开连接
            connection.disconnect();
        } catch (IOException | KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    private static class TrustAnyTrustManager implements X509TrustManager {

        /**
         * 该方法检查客户端的证书，若不信任该证书则抛出异常。由于我们不需要对客户端进行认证，
         * 因此我们只需要执行默认的信任管理器的这个方法。
         * JSSE中，默认的信任管理器类为TrustManager。
         */
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        /**
         * 该方法检查服务器的证书，若不信任该证书同样抛出异常。通过自己实现该方法，可以使之信任我们指定的任何证书。
         * 在实现该方法时，也可以简单的不做任何处理，即一个空的函数体，由于不会抛出异常，它就会信任任何证书。
         */
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        /**
         * 返回受信任的X509证书数组。
         */
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }


    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

}
    	
    	
