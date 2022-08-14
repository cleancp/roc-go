package com.today.designPatterns.单例模式;

import lombok.Data;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月09日 01:06*
 * log.info()
 */
@Data
public final class SingletonEnumHolder {

    private byte[] data = new byte[1024];

    private SingletonEnumHolder() {

    }

    /**
     * 枚举方式增加懒加载
     */
    private enum EnumHolder {
        INSTANCE;
        SingletonEnumHolder instance;

        EnumHolder() {
            System.out.println("INSTANCE will be initialized immediately");
            this.instance = new SingletonEnumHolder();
        }

        public static void method() {
            //调用该方法则会主动使用SingletonEnum，INSTANCE将会被实例化
        }

        private SingletonEnumHolder getSingleton() {
            return instance;
        }
    }

    public static SingletonEnumHolder getInstance() {
        return EnumHolder.INSTANCE.getSingleton();
    }
}
