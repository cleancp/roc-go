package com.today.designPatterns.单例模式;

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
public final class LazySingleton {

    /**
     * 优点：懒加载
     * 缺点：多线程下会出现并发问题，可能导致多个实例出现
     *  结论：线程不安全 无法使用
     */

    private byte[] data = new byte[1024];

    private static LazySingleton instance = null;

    //构造私有
    private LazySingleton() {
    }

    public static LazySingleton getInstance(){
        //@1 A线程进来，== null ，进入@2
        //此时CPU轮换，B线程进来@1 、A线程未初始化instance，仍然为空B进入@2创建对象
        //CPU轮换，A在@2处继续执行，创建多个实例
        //@1
        if (instance == null){
            //@2
            instance = new LazySingleton();
        }
        return instance;
    }
}
