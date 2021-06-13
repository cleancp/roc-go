package com.today.roc.go.understand.thread.组件;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月23日 11:10*
 * log.info()
 */
public class TestExchanger {
    static final Exchanger<List<String>> exchanger = new Exchanger();
    static List<String> A = Lists.newArrayList();
    static List<String> B = Lists.newArrayList();

    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            List<String> listB = Lists.newArrayList();
            for (int i = 0; i < 10; i++) {
                listB.add("B"+i);
            }
            try {
                B =exchanger.exchange(listB);
                //System.out.println("BBB"+exchanger.exchange(listB));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            List<String> listA = Lists.newArrayList();
            for (int i = 0; i < 10; i++) {
                listA.add("A"+i);
            }
            try {
                A = exchanger.exchange(listA);
                //System.out.println("AAA"+exchanger.exchange(listA));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("A"+A);
        System.out.println("---------------------");
        System.out.println("B"+B);
    }




}
