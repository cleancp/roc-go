package com.today.thread.高并发编程详解.volatile关键字;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.concurrent.CountDownLatch;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月08日 20:13*
 * log.info()
 */
public class ThreeSpecialClass {

    public static final int init_value = 0;
    public static final int MAX        = 5;

    public volatile static int count = 0;
    private static boolean initialized = false;
    private static Context context;

    public static void main(String[] args) throws InterruptedException, NamingException {
        /***原子性：一个或多个操作不可分割 ， 要么都成功要么都失败**/
        //atomic();
        /**可见性：**/
        //visiable();
        //有序性
        //orderliness();
        //volatile非原子性
        testVolatileNoAtomicity();
    }

    private static void atomic() {
        int x = 0;
        x++;// 实际是 x = 0   x+1   为x赋值x+1
    }

    private static void visiable() {
        //因为CPU Cache模型原因， init_value会被加载到CPU Cache中，也就是线程的本地内存副本，导致线程读取的是副本的值，没有读取到主存的值
        new Thread(() -> {
            int local_value = init_value;
            while (local_value < MAX) {
                if (local_value != init_value) {
                    System.out.println(Thread.currentThread().getName() + " local_value =" + local_value + " x = " + init_value);
                    local_value = init_value;
                }
            }
        }, "reader").start();
    }

    public static Context orderliness() throws NamingException {
        /**有序性：处理器为了提高效率会进行指令重排序 ， 单线程不受影响，多线程并发时可能出现问题**/
        if (!initialized) {
            /**有序性
             * 以下进行指令重排，可能导致context为null ，先执行了initialized = true
             * 这时其它线程发现initialized为true而不去初始化context，导致context为null
             */
            context = new InitialContext();
            /**使用volatile修饰，保证在initialized = true时 上面代码已经执行完毕。
             * 这里必须对initialized加volatile，同时保证可见性，防止重复初始化
             * 对context加volatile可以保证content初始化在initialized=true之前执行，
             * 但是无法保证content初始化原子性，而且无法保证initialized对其它线程可见
             * */
            initialized = true;
        }
        return context;
    }

    public static void testVolatileNoAtomicity() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    count++;
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(count);
    }

}
