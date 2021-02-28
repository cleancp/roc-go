package com.today.roc.go.base.redis.resp;

import ch.qos.logback.classic.net.SimpleSocketServer;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年01月24日 20:59*
 * log.info()
 */
public class RedisServerSocket {


    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6379)) {
            Socket accept = serverSocket.accept();
            byte[] result = new byte[2048];
            accept.getInputStream().read(result);
            System.out.println(new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
