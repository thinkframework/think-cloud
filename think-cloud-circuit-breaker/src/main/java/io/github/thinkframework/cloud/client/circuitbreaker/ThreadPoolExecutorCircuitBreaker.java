package io.github.thinkframework.cloud.client.circuitbreaker;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 线程池
 */
public class ThreadPoolExecutorCircuitBreaker  extends CircuitBreakerBase {
    private ThreadPoolExecutor threadPoolExecutor = null;

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4); // TODO 设置成和线程池一样大小

    public ThreadPoolExecutorCircuitBreaker(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Override
    public <T> T run(Supplier<T> toRun, Function<Throwable, T> fallback) {
        try {
            Future<T> t = threadPoolExecutor.submit(() -> toRun.get());
            scheduledExecutorService.schedule(() -> t.cancel(true),
                    timeoutInMilliseconds, TimeUnit.MILLISECONDS);
             return t.get();
        } catch (InterruptedException |ExecutionException | CancellationException e) {
            return fallback.apply(e);
        } finally {
            //
        }
    }
}
