package io.github.thinkframework.cloud.client.circuitbreaker.config;

import io.github.thinkframework.cloud.client.circuitbreaker.CircuitBreaker;
import io.github.thinkframework.cloud.client.circuitbreaker.DefaultCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThinkCircuitBreakerConfiguration {

    @Bean
    public CircuitBreaker thinkCircuitBreaker(){
        return new DefaultCircuitBreaker();
    }
}
