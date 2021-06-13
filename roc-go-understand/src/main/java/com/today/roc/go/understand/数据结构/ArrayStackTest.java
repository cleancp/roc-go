package com.today.roc.go.understand.数据结构;

import org.apache.tomcat.util.digester.ArrayStack;

import java.util.ArrayList;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月22日 14:31*
 * log.info()
 */
public class ArrayStackTest {

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
//        //不报错
//        for (int i = 0; i < stack.size(); i++) {
//            Integer pop = stack.pop();
//            System.out.println(pop);
//        }
        //报错 Exception in thread "main" java.util.ConcurrentModificationException
//        stack.forEach(
//                v->{
//                    Integer pop = stack.pop();
//                    System.out.println(pop);
//                }
//        );
        System.out.println(stack.peek());
        Integer pop = stack.pop();//弹出最后进的数据9
        System.out.println(stack.peek());//输出8
    }

}
