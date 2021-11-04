package io.github.thinkframework.cloud.client.loadbalancer;

import io.github.thinkframework.cloud.client.ServiceInstance;

/**
 *
 * @param <T>
 * @see org.springframework.cloud.client.loadbalancer.LoadBalancerRequest
 */
public interface LoadBalancerRequest<T> {

    T apply(ServiceInstance instance) throws Exception;

}
