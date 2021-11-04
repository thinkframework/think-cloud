package io.github.thinkframework.cloud.client.circuitbreaker;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;

import java.util.function.Function;
import java.util.function.Supplier;

public class SpringCircuitBreaker implements CircuitBreaker {

    private io.github.thinkframework.cloud.client.circuitbreaker.CircuitBreaker circuitBreaker;

    public SpringCircuitBreaker(io.github.thinkframework.cloud.client.circuitbreaker.CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    @Override
    public <T> T run(Supplier<T> toRun) {
        return circuitBreaker.run(toRun);
    }

    @Override
    public <T> T run(Supplier<T> toRun, Function<Throwable, T> fallback) {
        return circuitBreaker.run(toRun, fallback);
    }
}
