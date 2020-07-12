package com.today.roc.go.common.utils.file;

import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月12日 22:46*
 * log.info()
 */
public class NetUtils {

    public static void main(String[] args) throws IOException {
//        testInetAddress();
        testURL();
    }

    public static void testURL() throws IOException {
        URL url = new URL("https://www.baidu.com");
        //字节流
        InputStream inputStream = url.openStream();
        //字符流
        InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8.displayName());
        //缓冲流
        BufferedReader br = new BufferedReader(isr);
        String line;
        while (Objects.nonNull(line = br.readLine())) {
            System.out.println(line);
        }
        br.close();
    }

    public static void testInetAddress() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        String hostName = localHost.getHostName();
        System.out.println("hostAddress：" + hostAddress + " hostName：" + hostName);
        InetAddress[] allByName = InetAddress.getAllByName(InetAddress.getLocalHost().getHostAddress());
        InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
        System.out.println(loopbackAddress.getCanonicalHostName());
        System.out.println(loopbackAddress.getHostAddress());
        System.out.println(loopbackAddress.getHostName());

    }

}
