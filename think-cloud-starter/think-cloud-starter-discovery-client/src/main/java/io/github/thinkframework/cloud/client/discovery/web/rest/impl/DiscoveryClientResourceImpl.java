package io.github.thinkframework.cloud.client.discovery.web.rest.impl;

import io.github.thinkframework.cloud.client.discovery.web.rest.DiscoveryClientResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @see org.springframework.cloud.client.discovery.DiscoveryClient
 */
@RestController
public class DiscoveryClientResourceImpl implements DiscoveryClientResource {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * Default order of the discovery client.
     */
    int DEFAULT_ORDER = 0;

    /**
     * A human-readable description of the implementation, used in HealthIndicator.
     * @return The description.
     */
    @Override
    public String description() {
        throw new UnsupportedOperationException("");
    }

    /**
     * Gets all ServiceInstances associated with a particular serviceId.
     * @param serviceId The serviceId to query.
     * @return A List of ServiceInstance.
     */
    @Override
    public List<ServiceInstance> getInstances(@PathVariable("servicedId") String serviceId){
        return discoveryClient.getInstances(serviceId);
    }

    /**
     * @return All known service IDs.
     */
    @Override
    public List<String> getServices() {
        return discoveryClient.getServices();
    }

    /**
     * Can be used to verify the client is valid and able to make calls.
     * <p>
     * A successful invocation with no exception thrown implies the client is able to make
     * calls.
     * <p>
     * The default implementation simply calls {@link #getServices()} - client
     * implementations can override with a lighter weight operation if they choose to.
     */
    @Override
    public void probe() {
        getServices();
    }

//    /**
//     * Default implementation for getting order of discovery clients.
//     * @return order
//     */
//    @Override
//    default int getOrder() {
//        return DEFAULT_ORDER;
//    }

}
