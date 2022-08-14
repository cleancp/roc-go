package com.today.thread.synchronous;

import java.util.stream.IntStream;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description 叫号机
 * @createTime 2021年02月27日 23:28*
 * log.info()
 */
public class InconsistentDataProblem {


    public static final int MAX = 20;
    private static int    index;
    private static Object obj = new Object();

    static class CallTask implements Runnable {

        @Override
        public void run() {
            synchronized (obj){
                while (index < MAX) {
                    index++;
                    System.out.println(Thread.currentThread().getName() + " 开始叫号 号码为 " + index);
                }
            }
        }
    }

    public static void main(String[] args) {
        //CallTask task = new CallTask();
        IntStream.range(0, 4).forEach(
                v -> {
                    //不同对象对自身obj的同步 导致数据不一致问题
                    new Thread(new CallTask(), "窗口" + v).start();
                }
        );
    }

}
