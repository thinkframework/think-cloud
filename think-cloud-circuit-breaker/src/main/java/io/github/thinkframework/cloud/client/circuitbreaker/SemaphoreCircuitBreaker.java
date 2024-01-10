package io.github.thinkframework.cloud.client.circuitbreaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 信号量
 * 支持请求
 * 超过一定数量则卡死
 */
public class SemaphoreCircuitBreaker  extends CircuitBreakerBase {
    private static final Logger logger = LoggerFactory.getLogger(SemaphoreCircuitBreaker.class);
    private Semaphore semaphore;

    @Override
    public <T> T run(Supplier<T> toRun, Function<Throwable, T> fallback) {
        try {
            semaphore.acquire();
            return toRun.get();
        } catch (InterruptedException e) {
            return fallback.apply(e);
        } finally {
            semaphore.release();
        }
    }

    public SemaphoreCircuitBreaker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
}
