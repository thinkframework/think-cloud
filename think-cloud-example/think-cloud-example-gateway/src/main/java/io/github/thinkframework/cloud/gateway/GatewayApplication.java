package io.github.thinkframework.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {
    
    public static void main(String[] args) throws Exception {
        SpringApplication springApplication = new SpringApplication(GatewayApplication.class);
        springApplication.setHeadless(false);
        springApplication.run(args);
    }
}
