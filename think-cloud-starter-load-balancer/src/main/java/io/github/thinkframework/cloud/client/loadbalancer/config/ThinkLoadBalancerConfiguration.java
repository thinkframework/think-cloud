package io.github.thinkframework.cloud.client.loadbalancer.config;

import io.github.thinkframework.cloud.client.discovery.DiscoveryClient;
import io.github.thinkframework.cloud.client.loadbalancer.DefaultLoadBalancerClient;
import io.github.thinkframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThinkLoadBalancerConfiguration {

    @Bean
    public LoadBalancerClient loadBalancerClient(DiscoveryClient discoveryClient) {
        return new DefaultLoadBalancerClient(discoveryClient);
    }
}
