package io.github.thinkframework.cloud.client.circuitbreaker.config;

import io.github.thinkframework.cloud.client.circuitbreaker.ThinkCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ThinkCircuitBreakerConfiguration {

    @Bean
    public CircuitBreakerFactory ThinkCircuitBreakerFactory() {
        return new ThinkCircuitBreakerFactory();
    }
}
