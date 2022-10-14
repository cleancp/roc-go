package com.today.thread.parent_son_thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * description：
 *
 * @author：roc.zou 2022/10/7 3:30 下午
 */
@Slf4j
public class TransmittableThreadLocalLearn {
    private static final ThreadLocal<String> contextHolder = new TransmittableThreadLocal();

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i + "");
        }
        Executor executor = build();
        list.forEach(id -> {
            contextHolder.set(id);
            System.out.println(Thread.currentThread().getName() + "----" + contextHolder.get());
            Runnable runnable = () -> {
                System.out.println(Thread.currentThread().getName() + "----" + contextHolder.get());
            };
            executor.execute(runnable);
            new Thread(runnable).start();
        });
    }

    private static Executor build() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程池大小
        executor.setCorePoolSize(5);
        // 最大可创建的线程数
        executor.setMaxPoolSize(10);
        // 队列最大长度
        executor.setQueueCapacity(1000);
        // 线程池维护线程所允许的空闲时间
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("taskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return TtlExecutors.getTtlExecutor(executor);
    }
}
