package io.github.thinkframework.cloud.client.loadbalance;

import io.github.thinkframework.cloud.client.loadbalancer.DefaultLoadBalancerClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;
import org.springframework.cloud.client.loadbalancer.LoadBalancerUriTools;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MockLoadBalancerClient implements org.springframework.cloud.client.loadbalancer.LoadBalancerClient{

    @Resource
    private DefaultLoadBalancerClient loadBalancerClient;

    @Override
    public <T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException {
        return execute(serviceId,choose(serviceId),request);
    }

    @Override
    public <T> T execute(String serviceId, ServiceInstance serviceInstance, LoadBalancerRequest<T> request) throws IOException {
        try {
            return request.apply(serviceInstance);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public URI reconstructURI(ServiceInstance instance, URI original) {
        return LoadBalancerUriTools.reconstructURI(instance, original);
    }

    @Override
    public ServiceInstance choose(String serviceId) {
        // 解耦
        return new ServiceInstance() {
            @Override
            public String getServiceId() {
                return "word";
            }

            @Override
            public String getHost() {
                return "127.0.0.1";
            }

            @Override
            public int getPort() {
                return 8080;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public URI getUri() {
                return URI.create("http://word/api/hello");
            }

            @Override
            public Map<String, String> getMetadata() {
                return new HashMap<>();
            }
        };
    }
}
