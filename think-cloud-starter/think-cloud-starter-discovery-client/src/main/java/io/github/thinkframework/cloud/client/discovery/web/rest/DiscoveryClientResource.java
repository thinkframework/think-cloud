package io.github.thinkframework.cloud.client.discovery.web.rest;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *
 * @see org.springframework.cloud.client.discovery.DiscoveryClient
 */
@RequestMapping("/api/think/discovery-clients")
public interface DiscoveryClientResource { // extends Ordered {

    /**
     * Default order of the discovery client.
     */
    int DEFAULT_ORDER = 0;

    /**
     * A human-readable description of the implementation, used in HealthIndicator.
     * @return The description.
     */
    String description();

    /**
     * Gets all ServiceInstances associated with a particular serviceId.
     * @param serviceId The serviceId to query.
     * @return A List of ServiceInstance.
     */
    @GetMapping("services/{serviceId}")
    List<ServiceInstance> getInstances(@PathVariable("servicedId") String serviceId);

    /**
     * @return All known service IDs.
     */
    @GetMapping("services")
    List<String> getServices();

    /**
     * Can be used to verify the client is valid and able to make calls.
     * <p>
     * A successful invocation with no exception thrown implies the client is able to make
     * calls.
     * <p>
     * The default implementation simply calls {@link #getServices()} - client
     * implementations can override with a lighter weight operation if they choose to.
     */
    default void probe() {
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
