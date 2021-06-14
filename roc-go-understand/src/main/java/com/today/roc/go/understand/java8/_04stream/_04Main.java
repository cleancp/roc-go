package com.today.roc.go.understand.java8._04stream;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年06月14日 19:36*
 * log.info()
 */
public class _04Main {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(11, 12, 3, 5, 16, 8);
        //testFunc(list);
        //排序
        list.sort(Comparator.comparingInt(v -> (int) v).reversed());
        list.forEach(v -> System.out.println(v));
        List<List<Integer>> listList = Lists.newArrayList(list, list, list, list);
        List<Stream<Object>> mapRes = listList.stream().map(v -> Arrays.stream(v.toArray())).collect(Collectors.toList());
        //flatMap 使结果扁平化
        List<Integer> flatMapRes = listList.stream().flatMap(v -> Arrays.stream(v.toArray()))
                .map(v1 -> (Integer) v1).collect(Collectors.toList());
        
    }

    private static void testFunc(List<Integer> list) {
        Integer integer = list.stream()
                //映射 Function apply
                .map(v -> v * 10)
                //过滤 Predicate test
                .filter(v -> v < 1000)
                //排序 Comparator
                .sorted((num1, num2) -> num1 - num2)
                //去重
                .distinct()
                //收集  Collector
                //.collect(Collectors.toList())
                //规约
                .reduce((num1, num2) -> num1 + num2)
                .get();
        System.out.println(integer);

        list.stream().forEach(v -> System.out.println(v));
        //升级版本
        list.stream().forEach(System.out::println);
        //findAny输出的结果不稳定
        list.stream().findAny().ifPresent(s -> System.out.println(s));
        //findFirst输出结果稳定
        List<String> strList = Lists.newArrayList();
        strList.stream().findFirst().ifPresent(v -> System.out.println(v));
        boolean present = strList.stream().findFirst().isPresent();
        System.out.println(present);

        //为空会报错
        //String strr1 = strList.stream().findFirst().get();
        //System.out.println(strr1);
    }
}
