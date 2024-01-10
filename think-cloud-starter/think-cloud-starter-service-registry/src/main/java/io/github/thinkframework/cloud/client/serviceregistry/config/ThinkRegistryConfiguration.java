package io.github.thinkframework.cloud.client.serviceregistry.config;

import io.github.thinkframework.cloud.client.serviceregistry.DefaultServiceConfig;
import io.github.thinkframework.cloud.client.serviceregistry.DefaultServiceRegistry;
import io.github.thinkframework.cloud.client.serviceregistry.ServiceConfig;
import io.github.thinkframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class ThinkRegistryConfiguration {

    /**
     * TODO 面向接口,不要面向实现
     * @return
     */
    @Bean
    public DefaultServiceRegistry thinkServiceRegistry() {
        return new DefaultServiceRegistry(thinkServiceConfig());
    }

    @Bean
    public ServiceConfig thinkServiceConfig() {
        return new DefaultServiceConfig();
    }

}
