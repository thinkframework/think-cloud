package io.github.thinkframework.cloud.client.loadbalancer.config;

import io.github.thinkframework.cloud.client.loadbalancer.SpringLoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringLoadBalancerConfiguration {

    @Bean
    public LoadBalancerClient serviceRegistry(io.github.thinkframework.cloud.client.loadbalancer.LoadBalancerClient loadBalancerClient) {
        return new SpringLoadBalancerClient(loadBalancerClient);
    }
}
