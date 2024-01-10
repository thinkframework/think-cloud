package io.github.thinkframework.cloud.client.circuitbreaker;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 默认断路器
 * 什么都不做
 */
public class DefaultCircuitBreaker extends CircuitBreakerBase {

    @Override
    public <T> T run(Supplier<T> toRun, Function<Throwable, T> fallback) {
        return toRun.get();
    }
}
