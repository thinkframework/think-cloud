package io.github.thinkframework.cloud.client.loadbalancer;

import io.github.thinkframework.cloud.client.ServiceInstance;

/**
 *
 * @see org.springframework.cloud.client.loadbalancer.ServiceInstanceChooser
 *
 */
public interface ServiceInstanceChooser {

    /**
     * Chooses a ServiceInstance from the LoadBalancer for the specified service.
     * @param serviceId The service ID to look up the LoadBalancer.
     * @return A ServiceInstance that matches the serviceId.
     */
    ServiceInstance choose(String serviceId);
}
