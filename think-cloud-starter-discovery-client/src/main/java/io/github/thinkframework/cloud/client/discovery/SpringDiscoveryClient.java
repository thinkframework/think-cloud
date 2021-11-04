package io.github.thinkframework.cloud.client.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpringDiscoveryClient implements DiscoveryClient {

    @Autowired
    private io.github.thinkframework.cloud.client.discovery.DiscoveryClient discoveryClient;

    public SpringDiscoveryClient(io.github.thinkframework.cloud.client.discovery.DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public String description() {
        return discoveryClient.description();
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {
        return discoveryClient.getInstances(serviceId)
            .stream().map(serviceInstance ->  new ServiceInstance(){

                @Override
                public String getInstanceId() {
                    return serviceInstance.getInstanceId();
                }

                @Override
                public String getServiceId() {
                    return serviceInstance.getServiceId();
                }

                @Override
                public String getHost() {
                    return serviceInstance.getHost();
                }

                @Override
                public int getPort() {
                    return serviceInstance.getPort();
                }

                @Override
                public boolean isSecure() {
                    return serviceInstance.isSecure();
                }

                @Override
                public URI getUri() {
                    return serviceInstance.getUri();
                }

                @Override
                public Map<String, String> getMetadata() {
                    return serviceInstance.getMetadata();
                }

                @Override
                public String getScheme() {
                    return serviceInstance.getScheme();
                }
            }).collect(Collectors.toList());
    }

    @Override
    public List<String> getServices() {
        return discoveryClient.getServices();
    }
}
