package io.github.thinkframework.cloud.client.circuitbreaker.config;

import io.github.thinkframework.cloud.client.circuitbreaker.CircuitBreaker;
import io.github.thinkframework.cloud.client.circuitbreaker.SpringCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringCircuitBreakerConfiguration {

    @Bean
    public org.springframework.cloud.client.circuitbreaker.CircuitBreaker springCircuitBreaker(CircuitBreaker circuitBreaker){
        return new SpringCircuitBreaker(circuitBreaker);
    }
}
