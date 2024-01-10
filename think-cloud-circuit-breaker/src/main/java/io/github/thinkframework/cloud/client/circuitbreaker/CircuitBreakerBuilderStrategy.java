package io.github.thinkframework.cloud.client.circuitbreaker;

public enum CircuitBreakerBuilderStrategy {
    SEMAPHORE,
    THREAD_POOL_EXECUTOR,
}