package com.today.java8._01default;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年06月13日 20:10*
 * log.info()
 */
public class TestDefaultImpl implements TestDefault {
    @Override
    public void say() {
        System.out.println("say");
    }

    @Override
    public void eat(String food) {
        System.out.println("实现类："+food);
    }
}
