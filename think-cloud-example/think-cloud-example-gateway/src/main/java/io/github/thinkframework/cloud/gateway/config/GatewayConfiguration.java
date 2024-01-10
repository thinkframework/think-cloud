package io.github.thinkframework.cloud.gateway.config;

import org.springframework.cloud.client.loadbalancer.reactive.DeferringLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public DeferringLoadBalancerExchangeFilterFunction deferringLoadBalancerExchangeFilterFunction() {
        return new DeferringLoadBalancerExchangeFilterFunction(null);
    }
}
