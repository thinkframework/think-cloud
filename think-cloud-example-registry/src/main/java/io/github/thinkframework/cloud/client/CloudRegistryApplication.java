package io.github.thinkframework.cloud.client;

import io.github.thinkframework.cloud.client.circuitbreaker.EnableThinkCircuitBreaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 注册中心
 */
@EnableThinkCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class CloudRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudRegistryApplication.class,args);
    }
}
