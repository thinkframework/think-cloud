package io.github.thinkframework.cloud.client.circuitbreaker;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 默认断路器
 * 什么都不做
 */
public abstract class CircuitBreakerBase implements CircuitBreaker {

    protected int timeoutInMilliseconds;
    protected int maxConcurrentRequests;

    public int getTimeoutInMilliseconds() {
        return timeoutInMilliseconds;
    }

    public void setTimeoutInMilliseconds(int timeoutInMilliseconds) {
        this.timeoutInMilliseconds = timeoutInMilliseconds;
    }

    public int getMaxConcurrentRequests() {
        return maxConcurrentRequests;
    }

    public void setMaxConcurrentRequests(int maxConcurrentRequests) {
        this.maxConcurrentRequests = maxConcurrentRequests;
    }
}
