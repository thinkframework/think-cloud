package io.github.thinkframework.cloud.client.circuitbreaker;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableCircuitBreaker
public @interface EnableThinkCircuitBreaker {
}
