package io.github.thinkframework.cloud.client.circuitbreaker;

import io.github.thinkframework.cloud.client.circuitbreaker.config.ThinkCircuitBreakerConfig;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.ConfigBuilder;

import java.util.function.Function;

public class ThinkCircuitBreakerFactory extends CircuitBreakerFactory<ThinkCircuitBreakerConfig, ThinkCircuitBreakerFactory.ThinkCircuitBreakerConfigBuilder> {


    @Override
    public CircuitBreaker create(String id) {
        return null;
    }

    @Override
    protected ThinkCircuitBreakerConfigBuilder configBuilder(String id) {
        return null;
    }

    @Override
    public void configureDefault(Function<String, ThinkCircuitBreakerConfig> defaultConfiguration) {

    }

    public class ThinkCircuitBreakerConfigBuilder implements ConfigBuilder<ThinkCircuitBreakerConfig> {

        @Override
        public ThinkCircuitBreakerConfig build() {
            return null;
        }
    }
}
