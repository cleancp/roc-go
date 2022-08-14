package com.today.java8._02funcinterface;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年06月13日 20:26*
 * log.info()
 */
@Slf4j
public class _02Main {

    public static void main(String[] args) {
        //ownTest();
        // testBiConsumer();
        // streamTest();
        testExcelImport();
    }

    public static void testExcelImport() {
        List<FuncBean> beanList = Lists.newArrayList();
//        IntStream.range(1,5).forEach(
//                v->{
//                    FuncBean b1 = new FuncBean();
//                    b1.setName(v+"");
//                    beanList.add(b1);
//                }
//        );
        for (int i = 0; i < 5; i++) {
            FuncBean b1 = new FuncBean();
            b1.setName(i + "");
            beanList.add(b1);
        }
        FuncExecutor<FuncBean> executor1 = new FuncExecutor<>((funcBean) -> {
            log.info(funcBean.getName());
            return true;
        }, FuncBean::setErrorMsg, null, "错误信息");
        FuncHandler<FuncBean> handler = new FuncHandler<>();
        handler.setSuccessList(beanList);
        handler.handel("测试校验", Lists.newArrayList(executor1));
    }

    public static void streamTest() {
        List<Integer> list = Lists.newArrayList();
        IntStream.range(1, 5).forEach(v -> list.add(v));
        list.stream().collect(Collectors.toMap(v -> v, v -> v, (v1, v2) -> v1));
    }

    private static void ownTest() {
        _01FunctionInterface i = new _01FunctionInterface() {
            @Override
            public String say(String msg) {
                return null;
            }
        };
        //匿名内部类的改进 增加default方法支持
        _01FunctionInterface b = (a) -> "" + a;
        //大括号可省略
//        _01FunctionInterface b1 = a -> "" + a;
        String hello = b.say("hello");
        b.eat();
    }

    public static void testBiConsumer() {
        BiConsumer<Integer, Integer> biConsumer = new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer integer, Integer integer2) {
                System.out.println("value1:" + integer);
                System.out.println("value2:" + integer2);
            }
        };
        BiConsumer<Integer, Integer> biConsumer2 = new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer integer, Integer integer2) {
                System.out.println("biConsumer2 value1:" + integer);
                System.out.println("biConsumer2 value2:" + integer2);
            }
        };
        //biConsumer.accept(1,2);
        biConsumer.andThen(biConsumer2).accept(1, 2);
    }
}
