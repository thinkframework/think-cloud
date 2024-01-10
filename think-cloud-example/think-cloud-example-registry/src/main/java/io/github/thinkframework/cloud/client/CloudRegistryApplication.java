package io.github.thinkframework.cloud.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * 注册中心
 */
@SpringBootApplication
public class CloudRegistryApplication {
    public static void main(String[] args) {

        SpringApplication.run(CloudRegistryApplication.class,args);
    }
}
