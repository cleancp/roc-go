package com.today.thread.高并发编程详解.类的加载过程;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月06日 21:52*
 * log.info()
 */
public class Singleton {

    //1
    public static int x = 0;
    public static int y;

    private static Singleton instance = new Singleton();//2
    private Singleton(){
        x ++;
        y ++;
    }

    public static Singleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(Singleton.x); // 1 0
        System.out.println(Singleton.y); // 1 1
        //1 2 位置 输出 1,1
        //2放在1位置 输出 0 ，1
    }
}
