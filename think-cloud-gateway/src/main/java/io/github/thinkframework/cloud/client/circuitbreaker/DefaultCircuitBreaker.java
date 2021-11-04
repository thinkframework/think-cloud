package io.github.thinkframework.cloud.client.circuitbreaker;

import java.util.function.Function;
import java.util.function.Supplier;

public class DefaultCircuitBreaker implements CircuitBreaker {
    @Override
    public <T> T run(Supplier<T> toRun, Function<Throwable, T> fallback) {
        return null;
    }
}
