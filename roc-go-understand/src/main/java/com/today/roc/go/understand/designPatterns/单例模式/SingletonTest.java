package com.today.roc.go.understand.designPatterns.单例模式;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月09日 00:05*
 * log.info()
 */
public class SingletonTest {

    public static void main(String[] args) {
        //HungrySingleton instance = HungrySingleton.getInstance();
        //SingletonEnum.method();
        SingletonEnumHolder instance1 = SingletonEnumHolder.getInstance();
        SingletonEnumHolder instance2 = SingletonEnumHolder.getInstance();
        System.out.println(instance1 == instance2);
    }

}
