package com.today.thread.高并发编程详解.six;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年02月28日 21:29*
 * log.info()
 */
public class HookMain {

    public static final String LOCK_PATH = "E:\\java\\github-repository\\roc-go\\roc-go-understand\\src\\main\\java\\com\\today\\roc\\go\\understand\\thread\\six\\";
    public static final String LOCK_FILE = ".lock";

    public static void main(String[] args) throws IOException, InterruptedException {
        //testHook();
        hook();
    }

    private static void testHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    try {
                        System.out.println("The hook thread 1 is running.");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("The hook thread 1 will exit.");
                }
        ));

        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    try {
                        System.out.println("The hook thread 2 is running.");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("The hook thread 2 will exit.");
                }
        ));

        System.out.println("The program will is stopping");
    }


    /**
     * Hook实战
     */
    public static void hook() throws IOException, InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println(" program has exit ");
           if (getLookFile().toFile().exists()){
               //删除文件
               getLookFile().toFile().delete();
           }
        }));
        checkRunning();

        IntStream.range(0,5).forEach(v->{
            System.out.println(" the program is running ");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 检查文件是否存在，如果是第一次启动，创建文件
     */
    public static void checkRunning() throws IOException {
        Path path = getLookFile();
        if (path.toFile().exists()) {
            //文件存在，表示已经启动
            throw new RuntimeException("the program already running……");
        }
        Files.createFile(path);
        System.out.println("create file success");
    }

    public static Path getLookFile() {
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }

}
