package io.github.thinkframework.cloud.client.discovery.config;

import io.github.thinkframework.cloud.client.discovery.*;
import io.github.thinkframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDiscoveryClientConfiguration {

    @Bean
    public org.springframework.cloud.client.discovery.DiscoveryClient springDiscoveryClient(io.github.thinkframework.cloud.client.discovery.DiscoveryClient discoveryClient){
        return new SpringDiscoveryClient(discoveryClient);
    }
}
