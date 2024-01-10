package io.github.thinkframework.cloud.client.circuitbreaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.function.Function;
import java.util.function.Supplier;

public class A {

    protected static final Logger logger = LoggerFactory.getLogger(A.class);
    public static void main(String[] args) throws InterruptedException {
        CircuitBreaker circuitBreaker = CircuitBreakerBuilder.builder()
                .strategy(CircuitBreakerBuilderStrategy.THREAD_POOL_EXECUTOR)
                .maxConcurrentRequests(20)
                .timeoutInMilliseconds(5000)
                .build();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        circuitBreaker.run(new Supplier<Object>() {
            @Override
            public Object get() {
                try {
                    logger.debug("准备降级.");
                    Thread.sleep(10000l);
                    logger.debug("未降级.");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        }, new Function<Throwable, Object>() {
            @Override
            public Object apply(Throwable throwable) {
                logger.debug("超时降级成功.");
                countDownLatch.countDown();
                return null;
            }
        });
        countDownLatch.await();
    }
}
