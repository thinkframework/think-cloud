package io.github.thinkframework.cloud.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public GatewayRunner gatewayRunner(){
        return new GatewayRunner();
    }
}
