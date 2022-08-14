package com.today.utils.file;

import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
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
        String filePath = "E:\\java\\testfile\\Xftp-6.0.0105p.exe";
        String sourcePath = "E:\\java\\testfile\\alibaba\\Xftp-6.0.0105p.exe";
//        testInetAddress();
//        testURL();
//        fastCopy(sourcePath,filePath);

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

    public static void fastCopy(String sourcePath, String targetDist) throws IOException {
        Long start = System.currentTimeMillis();
        //获取输入文件字节流
        FileInputStream fis = new FileInputStream(new File(sourcePath));
        //获取输入字节流的文件通道
        FileChannel fisChannel = fis.getChannel();
        //获取输出文件字节流
        FileOutputStream fos = new FileOutputStream(new File(targetDist));
        //获取输出字节流的文件通道
        FileChannel fosChannel = fos.getChannel();
        //为缓冲区分配 1024 字节大小
        ByteBuffer buffer = ByteBuffer.allocateDirect(20*1024);

        while (true) {
            //将输入字节流读取到缓冲区中
            int read = fisChannel.read(buffer);
            //如果read= -1 表示EOF
            if (read == -1) {
                break;
            }
            //切换读写
            buffer.flip();
            //缓冲区写入目标文件
            fosChannel.write(buffer);
            //清除缓冲区
            buffer.clear();
        }
        System.out.println(System.currentTimeMillis()-start);
    }
}
