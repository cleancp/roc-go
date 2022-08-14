package com.today.utils.file;

import com.today.bo.JsonBO;
import com.today.utils.json.JsonUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月12日 16:16*
 * log.info()
 */
public class FileUtils {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filePath = "E:\\java\\testfile";
        String sourcePath = "E:\\java\\testfile\\alibaba\\Xftp-6.0.0105p.exe";
        String logFilePath = "E:\\java\\testfile\\info.log";
        //showListFile(new File(filePath));
        //copyFileTo(sourcePath, "E:\\java\\testfile");
        Long start = System.currentTimeMillis();
        copyFile(sourcePath, "E:\\java\\testfile\\Xftp-6.0.0105p.exe");
        System.out.println(System.currentTimeMillis() - start);
//        String decode = decode(encode("你好123abc,.?", StandardCharsets.UTF_8.displayName()), StandardCharsets.UTF_8.displayName());
//        System.out.println(decode);
//        readLine(logFilePath);
//        serialize();

    }

    /**
     * 类中所有属性都要序列化
     */
    public static void serialize() throws IOException, ClassNotFoundException {
        JsonBO jsonBO = JsonUtils.buildObject(JsonBO.class, 10);
        File file = new File("E:\\java\\testfile\\jsonBo");
        if (!file.exists()) {
            file.createNewFile();
        }
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(jsonBO);
        outputStream.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        JsonBO o = (JsonBO) ois.readObject();
        ois.close();
        System.out.println(o);
    }

    public static void readLine(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fileReader);
        String line;
        while (Objects.nonNull(line = br.readLine())) {
            System.out.println(line);
        }
        // 装饰者模式使得 BufferedReader 组合了一个 Reader 对象
        // 在调用 BufferedReader 的 close() 方法时会去调用 Reader 的 close() 方法
        // 因此只要一个 close() 调用即可
        br.close();
    }

    /**
     * 解码
     */
    public static String decode(byte[] bytes, String decode) throws UnsupportedEncodingException {
        return new String(bytes, decode);
    }

    /**
     * 编码
     */
    public static byte[] encode(String str, String encode) throws UnsupportedEncodingException {
        System.out.println(StandardCharsets.UTF_8.displayName());
        System.out.println(StandardCharsets.ISO_8859_1.displayName());
        System.out.println(StandardCharsets.UTF_16.displayName());
        System.out.println(StandardCharsets.UTF_16BE.displayName());
        byte[] bytes = str.getBytes(encode);
        return bytes;
    }

    /**
     * 文件拷贝
     */
    public static void copyFileTo(String sourcePath, String targetDist) throws IOException {
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetDist);
        if (Objects.isNull(sourceFile) || !sourceFile.exists()) {
            return;
        }
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        if (targetFile.isDirectory()) {
            targetDist = targetDist.concat(File.separator + sourceFile.getName());
            targetFile = new File(targetDist);
            targetFile.createNewFile();
        }
        copyFile(sourceFile.getAbsolutePath(), targetFile.getAbsolutePath());
    }


    public static void copyFile(String sourceFile, String targetFile) throws IOException {
        InputStream is = null;
        OutputStream out = null;
        try {
            is = new FileInputStream(sourceFile);
            out = new FileOutputStream(targetFile);
            byte[] buffer = new byte[20 * 1024];
            int cnt;
            //buffer是缓冲字节量 ， 一次最多读取buffer.length长度
            //cnt是读取位置 ， 为-1时表示读取完毕
            //看源码可知，如果这次buffer读取完毕时，返回末尾位置此时不为-1，下一次读取时直接返回-1
            while ((cnt = is.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, cnt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(is)) {
                is.close();
            }
            if (Objects.nonNull(out)) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 递归展示文件下所有文件
     */
    public static void showListFile(File file) {
        if (Objects.isNull(file) || !file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            System.out.println(file.getAbsolutePath());
            return;
        }
        System.out.println(file.getAbsolutePath());
        File[] files = file.listFiles();
        for (File file1 : files) {
            showListFile(file1);
        }
    }
}
