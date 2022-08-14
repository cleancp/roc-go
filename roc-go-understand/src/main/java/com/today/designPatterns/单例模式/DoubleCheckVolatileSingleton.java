package com.today.designPatterns.单例模式;

import javax.naming.NamingException;
import java.io.Serializable;

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
public class DoubleCheckVolatileSingleton implements Serializable {

    /**
     * 优点：懒加载，线程安全，性能可以
     * 结论：推荐
     */
    private byte[] data = new byte[1024];

    //Context context;
    //Socket  socket;

    //解决有序性问题
    private volatile static DoubleCheckVolatileSingleton instance = null;

    //构造私有
    private DoubleCheckVolatileSingleton() throws NamingException {
        //this.context = new InitialContext();
        //this.socket = new Socket();
    }

    /**
     * synchronized
     */
    public static DoubleCheckVolatileSingleton getInstance() throws NamingException {
        if (instance == null) {
            synchronized (DoubleCheckVolatileSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckVolatileSingleton();
                }
            }
        }
        return instance;
    }

}
