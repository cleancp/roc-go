package com.today.roc.go.understand.designPatterns.单例模式;

import lombok.Data;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月09日 00:12*
 * log.info()
 */
@Data
public final class LazySyncSingleton {

    /**
     * 优点：懒加载
     * 缺点：每次创建都要进入synchronized同步方法，多线程会阻塞，效率低
     *  结论：不推荐
     */

    private byte[] data = new byte[1024];

    private static LazySyncSingleton instance = null;

    //构造私有
    private LazySyncSingleton() {
    }

    //同步，每次只能一个线程进去
    public static synchronized LazySyncSingleton getInstance(){
        if (instance == null){
            instance = new LazySyncSingleton();
        }
        return instance;
    }
}
