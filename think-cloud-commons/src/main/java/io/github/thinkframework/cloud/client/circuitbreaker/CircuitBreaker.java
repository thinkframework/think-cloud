package io.github.thinkframework.cloud.client.circuitbreaker;

import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @see org.springframework.cloud.client.circuitbreaker.CircuitBreaker
 */
public interface CircuitBreaker {

    default <T> T run(Supplier<T> toRun) {
        return run(toRun, throwable -> {
            throw new NoFallbackAvailableException("No fallback available.", throwable);
        });
    };

    <T> T run(Supplier<T> toRun, Function<Throwable, T> fallback);
}
