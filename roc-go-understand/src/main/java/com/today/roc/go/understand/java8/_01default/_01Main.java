package com.today.roc.go.understand.java8._01default;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年06月13日 20:11*
 * log.info()
 */
public class _01Main {

    public static void main(String[] args) {
        TestDefault testDefault = new TestDefaultImpl();

        testDefault.say();
        testDefault.eat("");

        TestDefault testDefault1 = new TestDefault() {
            @Override
            public void say() {
                System.out.println("匿名内部类：say ");
            }
        };
        testDefault1.say();
    }

}
