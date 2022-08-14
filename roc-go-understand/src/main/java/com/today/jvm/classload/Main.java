package com.today.jvm.classload;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2022年07月16日 16:28*
 * log.info()
 */
public class Main {

    // Integer初始化值0 boolean 初始化值 false
    public static Integer value = 100;

    public String str = "你好";

    static {
        System.out.println("执行静态代码块 value值："+value);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.returnStr();
        System.out.println("main方法执行 value值："+value);
    }

    public String returnStr(){
        return str;
    }

}
