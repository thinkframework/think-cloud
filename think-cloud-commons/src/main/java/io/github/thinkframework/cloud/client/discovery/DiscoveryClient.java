package io.github.thinkframework.cloud.client.discovery;

import io.github.thinkframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * TODO 看吧,要不要实现排序
 * @see org.springframework.cloud.client.discovery.DiscoveryClient
 */
public interface DiscoveryClient { // extends Ordered {

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
    List<ServiceInstance> getInstances(String serviceId);

    /**
     * @return All known service IDs.
     */
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
