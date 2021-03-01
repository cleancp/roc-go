package com.today.roc.go.understand.thread.seven;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月01日 23:25*
 * log.info()
 */
@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool threadPool);

    /**
     * 丢弃策略
     */
    class DiscardDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
        }
    }

    /**
     * 任务策略抛错
     */
    class AbortDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RuntimeDenyException("The runnable " + runnable + "will be abort.");
        }
    }

    /**
     * 在任务提交中执行
     */
    class RunnerDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }
}
