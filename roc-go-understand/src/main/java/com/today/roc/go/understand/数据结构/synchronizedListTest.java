package com.today.roc.go.understand.数据结构;

import org.apache.tomcat.util.digester.ArrayStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月22日 14:43*
 * log.info()
 */
public class synchronizedListTest {

    public static void main(String[] args) throws InterruptedException {
        ArrayStack<Integer> stack = new ArrayStack<>();
        List<Integer> list = new ArrayList<>();
        CopyOnWriteArrayList copy = new CopyOnWriteArrayList<>(new ArrayList<>());
        List<Integer> integers = Collections.synchronizedList(list);
        //报错 Exception in thread "main" java.util.ConcurrentModificationException
//        integers.forEach(
//                v->{
//                    Integer pop = stack.pop();
//                    System.out.println(pop);
//                }
//        );
//        ArrayList list = new ArrayList<Integer>();
//        List list1 = Collections.synchronizedList(list);
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                integers.add(i);
                copy.add(i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 10000; i < 20000; i++) {
                integers.add(i);
                copy.add(i);
            }
        }).start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(integers.size());
        System.out.println(list.size());
        System.out.println(copy.size());
//        Collections.synchronizedMap()
    }
}
