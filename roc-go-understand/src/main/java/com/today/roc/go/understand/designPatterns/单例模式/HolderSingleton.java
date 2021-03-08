package com.today.roc.go.understand.designPatterns.单例模式;

import lombok.Data;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月09日 00:52*
 * log.info()
 */
@Data
public final class HolderSingleton {

    /**
     * 优点：懒加载，线程安全，高性能
     * 结论：推荐
     */

    private byte[] data = new byte[1024];

    //构造私有
    private HolderSingleton() {
    }

    /**
     * 内部类中定义实例
     * HolderSingleton初始化并不会创建实例，只有在调用getInstance的时候才会创建HolderSingleton
     * HolderSingleton实例创建过程编译器收集至<clinit>，clinit是同步方法
     */
    private static class Holder {
        public static HolderSingleton instance = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return Holder.instance;
    }


}
