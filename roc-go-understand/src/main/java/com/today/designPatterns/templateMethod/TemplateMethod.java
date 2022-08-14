package com.today.designPatterns.templateMethod;

/**
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年02月26日 18:13*
 * log.info()
 */
public class TemplateMethod {

    public final void print(String message){
        System.out.println("#####################");
        wrapPrint(message);
        System.out.println("#####################");
    }

    protected void wrapPrint(String message) {

    }

    public static void main(String[] args) {
        TemplateMethod method1 = new TemplateMethod(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*"+message+"*");
            }
        };
        method1.print("你好1");

        TemplateMethod method2 = new TemplateMethod(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println("+"+message+"+");
            }
        };
        method2.print("你好2");
    }

}
