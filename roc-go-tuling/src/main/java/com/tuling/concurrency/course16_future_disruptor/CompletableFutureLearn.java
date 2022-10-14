package com.tuling.concurrency.course16_future_disruptor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * description：
 *
 * @author：roc.zou 2022/10/14 7:05 下午
 */
@Slf4j
public class CompletableFutureLearn {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(()->{
            log.info("");
            return "任务一执行完";
        });

    }



}
