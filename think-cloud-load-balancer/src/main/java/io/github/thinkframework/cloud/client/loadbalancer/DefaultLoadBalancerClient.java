package io.github.thinkframework.cloud.client.loadbalancer;

import io.github.thinkframework.cloud.client.ServiceInstance;
import io.github.thinkframework.cloud.client.discovery.DiscoveryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;

/**
 *
 */
public class DefaultLoadBalancerClient implements LoadBalancerClient {

    private static final Logger logger = LoggerFactory.getLogger(DefaultLoadBalancerClient.class);

    protected DiscoveryClient discoveryClient;

    public DefaultLoadBalancerClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public <T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException {
        return execute(serviceId,choose(serviceId),request);
    }

    @Override
    public <T> T execute(String serviceId, ServiceInstance serviceInstance, LoadBalancerRequest<T> request) throws IOException {
        return null;
    }

    @Override
    public URI reconstructURI(ServiceInstance instance, URI original) {
        return URI.create("");
    }

    @Override
    public ServiceInstance choose(String serviceId) {
        // TODO 负载均衡
        return discoveryClient.getInstances(serviceId)
            .stream().findFirst()
            .get();
    }
}
