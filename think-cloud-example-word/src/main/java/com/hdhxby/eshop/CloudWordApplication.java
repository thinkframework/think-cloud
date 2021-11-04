package com.hdhxby.eshop;

import io.github.thinkframework.cloud.client.circuitbreaker.EnableThinkCircuitBreaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 权限中心
 *
 * @author lixiaobin
 * @version 2.0, 03/06/21
 * @since 2.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudWordApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(CloudWordApplication.class);
        springApplication.setHeadless(false);
        springApplication.run(args);
    }
}
