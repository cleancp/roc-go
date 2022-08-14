package com.today.oop.inner;

import lombok.Data;

/**
 * Software License Declaration.
 * <p>
 * zhilingsd.com, Co,. Ltd.
 * Copyright © 2016 All Rights Reserved.
 * <p>
 * Copyright Notice
 * This documents is provided to zhilingsd contracting agent or authorized programmer only.
 * This source code is written and edited by zhilingsd Co,.Ltd Inc specially for financial
 * business contracting agent or authorized cooperative company, in order to help them to
 * install, programme or central control in certain project by themselves independently.
 * <p>
 * Disclaimer
 * If this source code is needed by the one neither contracting agent nor authorized programmer
 * during the use of the code, should contact to zhilingsd Co,. Ltd Inc, and get the confirmation
 * and agreement of three departments managers  - Research Department, Marketing Department and
 * Production Department.Otherwise zhilingsd will charge the fee according to the programme itself.
 * <p>
 * Any one,including contracting agent and authorized programmer,cannot share this code to
 * the third party without the agreement of zhilingsd. If Any problem cannot be solved in the
 * procedure of programming should be feedback to zhilingsd Co,. Ltd Inc in time, Thank you!
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年11月04日 17:29*
 * log.info()
 */
@Data
public class TestInner {
    private Integer a = 1;
    static  Integer b = 2;

    /**
     * 成员内部类
     * 1、 Inner 类定义在 Outer 类的内部，相当于 Outer 类的一个成员变量的位置，Inner 类可以使用任意访问控制符，如 public 、 protected 、 private 等
     * 2、 Inner 类中定义的 test() 方法可以直接访问 Outer 类中的数据，而不受访问控制符的影响，如直接访问 Outer 类中的私有属性a
     * 3、 定义了成员内部类后，必须使用外部类对象来创建内部类对象，而不能直接去 new 一个内部类对象，即：内部类 对象名 = 外部类对象.new 内部类( );
     * 4、 编译上面的程序后，会发现产生了两个 .class 文件
     *
     * Java 编译器在创建内部类对象时，隐式的把其外部类对象的引用也传了进去并一直保存着。
     * 这样就使得内部类对象始终可以访问其外部类对象，同时这也是为什么在外部 类作用范围之外向要创建内部类对象必须先创建其外部类对象的原因。
     */
    @Data
    public class CInner {
        private Integer a = 2;
        private String  c = "c";

        public void test() {
            //访问外部类属性
            System.out.println("内部类访问外部类属性：" + TestInner.this.a);
            System.out.println("内部类属性 a " + a + " c " + c);
            System.out.println("CInner  test");
        }
    }

    /**
     * 静态内部类
     * 1、 静态内部类不能直接访问外部类的非静态成员，但可以通过 new 外部类().成员 的方式访问。
     * 2、 如果外部类的静态成员与内部类的成员名称相同，可通过“类名.静态成员”访问外部类的静态成员；
     *     如果外部类的静态成员与内部类的成员名称不相同，则可通过“成员名”直接调用外部类的静态成员。
     * 3、 创建静态内部类的对象时，不需要外部类的对象，可以直接创建 内部类 对象名= new 内部类();
     */
    @Data
    public static class SInner {
        private Integer a = 3;
        private String  s = "s";

        public void test() {
            //访问外部类属性
            System.out.println("内部类访问外部类静态变量：" + TestInner.b);
            System.out.println("a " + a + " s " + s);
            System.out.println("SInner  test");
        }
    }
    /**
     * 方法内部类
     * 方法内部类就是内部类定义在外部类的方法中，方法内部类只在该方法的内部可见，即只在该方法内可以使用。
     */
    public void show(){
        class MethodInner{
            public void test(){
                System.out.println("方法内部类调用");
            }
        }
        MethodInner mi = new MethodInner();
        mi.test();
    }

    /**
     * 匿名内部类
     */
    public abstract class AbstractInner{
        public abstract void test();
    }

    public static void main(String[] args) {
        TestInner testInner = new TestInner();
        CInner cInner = testInner.new CInner();
        System.out.println(cInner.toString());
        cInner.test();

        TestInner.SInner sInner = new TestInner.SInner();
        System.out.println(sInner.toString());
        sInner.test();

        testInner.show();

        //匿名内部类
        testInner.noNameInner();
    }

    private void noNameInner() {
        AbstractInner abstractInner = new AbstractInner() {
            @Override
            public void test() {
                System.out.println("匿名内部类");
            }
        };
        abstractInner.test();
    }
}
