package com.today.roc.go.understand.java8._03innerfunc;

import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年06月13日 23:48*
 * log.info()
 */
public class _03Main {

    public static void main(String[] args) {
        //consumer 消费函数式接口
        Consumer<String> consumer = (t) -> System.out.println("hello consumer " + t);
        Consumer<String> andThen = consumer.andThen((t) -> System.out.println("and Then " + t));
        andThen.accept("ha ha ha");

        //function T R 整形转字符串
        Function<Integer, String> f1 = (in) -> in + "";
        String r1 = f1.apply(111);
        System.out.println("r1:" + r1);
        //compose V T  return V R  字符转整形
        Function<Character, Integer> f2 = (a) -> (int) a.charValue();
        Character c1 = new Character('1');
        Integer r2 = f2.apply(c1);
        System.out.println("r2:" + r2);
        //字符转字符串 a转b c转a 有 c转b
        Function<Character, String> compose = f1.compose(f2);
        String r3 = compose.apply(c1);
        System.out.println("r3:" + r3);

        //Predicate 校验
        Predicate<String> p1 = (t) -> StringUtils.isNotBlank(t);
        boolean pr1 = p1.test("");
        System.out.println("pr1:" + pr1);

        //获取数据
        Supplier<String> s1 = () -> "return";
        String sr1 = s1.get();
        System.out.println("sr1：" + sr1);

        //Comparator 比较接口函数式编程
        Comparator<Integer> comparator1 = (num1, num2) -> num1 > num2 ? 1 : num1.equals(num2) ? 0 : -1;
        int cr1 = comparator1.compare(1, 2);
        System.out.println(cr1);
    }
}
