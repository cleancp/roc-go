package com.today.roc.go.understand.thread.组件;

import java.util.concurrent.*;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月23日 11:26*
 * log.info()
 */
public class TestFutureTask {

    public static void main(String[] args) throws InterruptedException {
        Executors.newSingleThreadExecutor();
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("进入call");
                //请求账单数据
                //TimeUnit.SECONDS.sleep(2);
                return "账单数据";
            }
        });
        new Thread(futureTask).start();
        //timeout 1S没获取到即超时返回
        String o = null;
        try {
            o = futureTask.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("超时直接返回空");
            o = "账单数据空";
        }
        System.out.println(o);
    }
}
