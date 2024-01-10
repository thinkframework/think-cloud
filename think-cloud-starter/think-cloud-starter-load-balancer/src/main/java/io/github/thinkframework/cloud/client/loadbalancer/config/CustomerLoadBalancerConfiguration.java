package io.github.thinkframework.cloud.client.loadbalancer.config;

import io.github.thinkframework.cloud.client.discovery.DiscoveryClient;
import io.github.thinkframework.cloud.client.loadbalancer.DefaultLoadBalancerClient;
import io.github.thinkframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.reactive.DeferringLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class CustomerLoadBalancerConfiguration {
    // TODO 为啥需要这个
    @Bean
    public DeferringLoadBalancerExchangeFilterFunction deferringLoadBalancerExchangeFilterFunction() {
        return new DeferringLoadBalancerExchangeFilterFunction(null);
    }
}
