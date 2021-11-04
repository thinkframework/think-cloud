package io.github.thinkframework.cloud.client.discovery.config;

import io.github.thinkframework.cloud.client.discovery.*;
import io.github.thinkframework.cloud.client.serviceregistry.Registration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@EnableConfigurationProperties({SpringClientConfig.class,SpringInstanceConfig.class})
@Configuration
public class ThinkDiscoveryClientConfiguration {

    /**
     * fixme 有时间做接口隔离
     * @param clientConfig
     * @param instanceConfig
     * @return
     */
    @Bean
    public DiscoveryClient thinkDdiscoveryClient(ClientConfig clientConfig,
                                                 InstanceConfig instanceConfig) {
        return new DefaultDiscoveryClient(clientConfig,instanceConfig,
            registration(instanceConfig),
            serviceRegistryHttpClient(clientConfig));
    }

    public Registration registration(InstanceConfig instanceConfig){
        return new DefaultServiceInstance.Builder().config(instanceConfig).build();
    }

    public ServiceRegistryHttpClient serviceRegistryHttpClient(ClientConfig clientConfig){
        return new ServiceRegistryHttpClientImpl(clientConfig);
    }

}
