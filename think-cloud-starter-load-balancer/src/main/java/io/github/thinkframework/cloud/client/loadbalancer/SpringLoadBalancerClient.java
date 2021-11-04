package io.github.thinkframework.cloud.client.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;
import org.springframework.cloud.client.loadbalancer.LoadBalancerUriTools;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

public class SpringLoadBalancerClient implements org.springframework.cloud.client.loadbalancer.LoadBalancerClient{

    private LoadBalancerClient loadBalancerClient;

    public SpringLoadBalancerClient(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    @Override
    public <T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException {
        return execute(serviceId, choose(serviceId), request);
    }

    @Override
    public <T> T execute(String serviceId, ServiceInstance serviceInstance, LoadBalancerRequest<T> request) throws IOException {
        try {
            return request.apply(serviceInstance);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * LoadBalancerInterceptor
     * 调用 LoadBalancerRequestFactory
     * 创建 包含 ServiceRequestWrapper
     *      ServiceRequestWrapper包含LoadBalancerClient和原始的HttpRequest
     * 的 LoadBalancerRequest
     * LoadBalancerInterceptor调用LoadBalancerClient的execute方法
     * 触发LoadBalancerRequest的apply方法
     *      123
     * ClientHttpRequestExecution执行过程中会调用ServiceRequestWrapper的getUri
     * 会调用LoadBalancerClient的reconstructURI方法
     * 传入 ServiceInstance和URI
     * 重建URI
     *
     * @see org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor
     * @see org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory
     * @see org.springframework.cloud.client.loadbalancer.LoadBalancerRequest
     * @see org.springframework.cloud.client.loadbalancer.ServiceRequestWrapper
     * @param instance
     * @param original
     * @return
     */
    @Override
    public URI reconstructURI(ServiceInstance instance, URI original) {
        return LoadBalancerUriTools.reconstructURI(instance, original);
    }

    @Override
    public ServiceInstance choose(String serviceId) {
        // think cloud内部表示
        io.github.thinkframework.cloud.client.ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
        // 解耦
        return new org.springframework.cloud.client.ServiceInstance() {
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
        };
    }


}
