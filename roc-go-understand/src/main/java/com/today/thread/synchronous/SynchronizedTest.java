package com.today.thread.synchronous;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年02月28日 00:04*
 * log.info()
 */
public class SynchronizedTest {

    private Object mutex = new Object();

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        IntStream.range(0, 5).forEach(
                v -> {
                    new Thread(test::accessResource).start();
                }
        );
    }

    public void accessResource() {
        synchronized (mutex) {
            try {
                TimeUnit.MINUTES.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
