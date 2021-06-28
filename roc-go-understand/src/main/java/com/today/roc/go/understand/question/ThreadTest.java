package com.today.roc.go.understand.question;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

import java.util.Currency;
import java.util.concurrent.TimeUnit;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年06月29日 00:10*
 * log.info()
 */
public class ThreadTest {

    //三线程按顺序交替打印ABC的四种方法
    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        CustomThread t1 = new CustomThread("A",c,a);
        CustomThread t2 = new CustomThread("B",a,b);
        CustomThread t3 = new CustomThread("C",b,c);

        new Thread(t1).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(t2).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(t3).start();
        TimeUnit.SECONDS.sleep(1);
    }

    @AllArgsConstructor
    static class CustomThread implements Runnable{
        String name ;
        Object prev ;
        Object self ;

        @Override
        public void run() {
            while (true){
                synchronized (prev){
                    synchronized (self){
                        System.out.println(name);
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        self.notifyAll();
                    }
                    try {
                        prev.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
