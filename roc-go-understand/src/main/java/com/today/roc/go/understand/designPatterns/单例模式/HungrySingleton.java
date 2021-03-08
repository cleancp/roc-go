package com.today.roc.go.understand.designPatterns.单例模式;

import lombok.Data;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description 饿汉式
 * @createTime 2021年03月09日 00:02*
 * log.info()
 */
@Data
//final 不能被继承
public final class HungrySingleton {

    /**
     * 优点：同步，getInstance()速度快
     * 缺点：1、不能懒加载，类初始化时就创建占用堆空间
     *      2、如果属性资源多和重，会造成空间占用
     *  结论：属性少可使用，整体不推荐
     */

    private byte[] data = new byte[1024];

    //在类进行初始化时就创建类变量instance从而触发创建实例，cinit是同步的所以不会存在并发问题
    private static HungrySingleton instance = new HungrySingleton();

    //构造私有
    private HungrySingleton() {
    }

    public static HungrySingleton getInstance(){
        return instance;
    }

}
