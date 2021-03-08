package com.today.roc.go.understand.designPatterns.单例模式;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.Socket;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月09日 00:27*
 * log.info()
 */
public class DoubleCheckSingleton {

    /**
     * 优点：懒加载
     * 缺点：可能会报错
     * 结论：不推荐
     */
    private byte[] data = new byte[1024];

    Context context;
    Socket  socket;

    private static DoubleCheckSingleton instance = null;

    //构造私有
    private DoubleCheckSingleton() throws NamingException {
        this.context = new InitialContext();
        this.socket = new Socket();
    }

    /**
     * synchronized
     * 有序性问题、当instance初始化完成时，不为空
     * 但属性还没有初始化完成，其它线程获取instance不为空，使用其中属性可能会报错
     */
    public static DoubleCheckSingleton getInstance() throws NamingException {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
