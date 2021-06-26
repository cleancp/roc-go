package com.today.roc.go.common.utils.lamda;

import com.google.common.collect.Lists;
import com.today.roc.go.common.bo.JsonBO;
import com.today.roc.go.common.utils.json.JsonUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月05日 22:44*
 * log.info()
 */
public class LamdaCorn {

    public static void main(String[] args) {
        //JsonBO jsonBO = JsonUtils.buildObject(JsonBO.class, 10);
        //testAnonymous();
        //排序
        //testSorted(jsonBO);
        //testStatistics(jsonBO);
        //testReduce(jsonBO);
        //testFilter(jsonBO);
//        list.stream().filter(v1 -> ObjectUtils.anyNotNull(v1.getCode(), v1.getShopId())).
//                collect(Collectors.toMap(v -> Optional.ofNullable(v.getCode()).orElse("") + Optional.ofNullable(v.getShopId()).orElse(0L), v -> v));


        List<User> userList = Lists.newArrayList();
        userList.add(new User(1,"1",1));
        userList.add(new User(1,"1",2));
        userList.stream().collect(Collectors.groupingBy(v->v.getId()+v.getName())).forEach(
                (k,v)->{
                    System.out.println(k+v.size());
                }
        );
        userList.stream().collect(Collectors.toMap(v -> v.getId() + v.getName(), v -> v,(v1,v2)->v1))
                .forEach(
                (k,v)->{
                    System.out.println(k+v.getAge());
                }
        );
    }

    public static void testReduce(JsonBO jsonBO) {
        List<Integer> integerList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        integerList.stream().map(v -> v + v * 12).collect(Collectors.toList()).forEach(v->{
            System.out.print(v+",");
        });
        System.out.println();

        System.out.println(integerList.stream().map(v -> v + v * 12).reduce((result, v) -> {
            return result + v;
        }).get());
        System.out.println(integerList.stream().reduce((result, v) -> {
            return result + v;
        }).get());
    }

    public static void testFilter(JsonBO jsonBO) {
        List<JsonBO.JsonSubBO> dataList = jsonBO.getDataList();
        dataList.stream().filter(v -> v.getSubname().equals("3")).forEach(v -> System.out.println(v));

    }

    public static void testStatistics(JsonBO jsonBO) {
        List<JsonBO.JsonSubBO> dataList = jsonBO.getDataList();
        //分组
        dataList.stream().collect(Collectors.groupingBy(JsonBO.JsonSubBO::getSubbirthday)).forEach(
                (k, v) -> {
                    System.out.println(k);
                    System.out.println(v);
                }
        );
        //计数
        dataList.stream().collect(Collectors.groupingBy(JsonBO.JsonSubBO::getSubbirthday, Collectors.counting())).forEach(
                (k, v) -> {
                    System.out.println(k);
                    System.out.println(v);
                }
        );
        //allMatch：Stream中全部元素符合传入的predicate返回 true
        System.out.println(dataList.stream().allMatch(v -> v.getSubage() == 4));
        //anyMatch：Stream中只要有一个元素符合传入的predicate返回 true
        System.out.println(dataList.stream().anyMatch(v -> v.getSubname().equals("2")));
        //noneMatch：Stream中没有一个元素符合传入的predicate返回 true
        System.out.println(dataList.stream().noneMatch(v -> v.getSubname().equals("0")));
    }

    public static void testSorted(JsonBO jsonBO) {
        List<JsonBO.JsonSubBO> dataList = jsonBO.getDataList();
        Map<String, JsonBO.JsonSubBO> dataMap = jsonBO.getDataMap();

        //升序方式一
        dataList.stream().sorted(new Comparator<JsonBO.JsonSubBO>() {
            @Override
            public int compare(JsonBO.JsonSubBO o1, JsonBO.JsonSubBO o2) {
                return o1.getSubage() > o2.getSubage() ? 0 : -1;
            }
        }).collect(Collectors.toList()).forEach(v -> System.out.println(v));

        //升序方式二
        dataList.stream().sorted(Comparator.comparing(JsonBO.JsonSubBO::getSubage)).forEach(v -> System.out.println(v));

        //降序方式二
        dataList.stream().sorted(new Comparator<JsonBO.JsonSubBO>() {
            @Override
            public int compare(JsonBO.JsonSubBO o1, JsonBO.JsonSubBO o2) {
                return o1.getSubage() > o2.getSubage() ? -1 : 0;
            }
        }).collect(Collectors.toList()).forEach(v -> System.out.println(v));
        //降序方式一
        dataList.stream().sorted(Comparator.comparing(JsonBO.JsonSubBO::getSubage).reversed()).forEach(v -> System.out.println(v));
    }

    //匿名函数
    public static void testAnonymous() {
        MethodOperation add = (int a, int b) -> (a + b);
        MethodOperation addState = (int a, int b) -> {
            System.out.println(a);
            System.out.println(b);
            return a + b;
        };
        MethodOperation substract = (int a, int b) -> (a - b);
        int addResult = operation(1, 2, addState);
        System.out.println(addResult);
        int substractResult = operation(3, 2, substract);
        System.out.println(substractResult);

        //1.匿名内部类实现多线程的
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "  启动成功");
            }
        }).start();

        //2.lambda表达式实现多线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "  启动成功");
        }).start();
    }

    interface MethodOperation {
        int operation(int a, int b);
    }

    public static int operation(int a, int b, MethodOperation methodOperation) {
        return methodOperation.operation(a, b);
    }

}
