package io.github.thinkframework.cloud.client.circuitbreaker;

import java.util.concurrent.*;

public class CircuitBreakerBuilder {


    public static Builder builder(){
        return new Builder();
    }

    static class Builder {

        private CircuitBreakerBuilderStrategy strategy;

        private int timeoutInMilliseconds;
        private int maxConcurrentRequests;

        public Builder strategy(CircuitBreakerBuilderStrategy strategy){
            this.strategy = strategy;
            return this;
        }


        public Builder timeoutInMilliseconds(int timeoutInMilliseconds){
            this.timeoutInMilliseconds = timeoutInMilliseconds;
            return this;
        }


        public Builder maxConcurrentRequests(int maxConcurrentRequests){
            this.maxConcurrentRequests = maxConcurrentRequests;
            return this;
        }

        public CircuitBreaker build(){
            CircuitBreaker circuitBreaker;
            if (strategy == strategy.SEMAPHORE){
                Semaphore semaphore = new Semaphore(maxConcurrentRequests);
                circuitBreaker = new SemaphoreCircuitBreaker(semaphore);
            }else if(strategy == strategy.THREAD_POOL_EXECUTOR) {
                int corePoolSize = 1;
                int maximumPoolSize = maxConcurrentRequests;
                long keepAliveTime = timeoutInMilliseconds * 3;
                TimeUnit unit = TimeUnit.MILLISECONDS;
                BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
                RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
                ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,handler);
                circuitBreaker = new ThreadPoolExecutorCircuitBreaker(threadPoolExecutor);
                ((ThreadPoolExecutorCircuitBreaker) circuitBreaker).setTimeoutInMilliseconds(timeoutInMilliseconds);
            }else {
                circuitBreaker =new DefaultCircuitBreaker();
            }
            return circuitBreaker;
        }
    }
}
