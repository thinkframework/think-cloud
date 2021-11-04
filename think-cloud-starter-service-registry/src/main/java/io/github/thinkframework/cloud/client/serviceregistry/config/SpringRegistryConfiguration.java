package io.github.thinkframework.cloud.client.serviceregistry.config;

import io.github.thinkframework.cloud.client.serviceregistry.SpringServiceRegistry;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringRegistryConfiguration {

    @Bean
    public ServiceRegistry springServiceRegistry() {
        return new SpringServiceRegistry();
    }
}
