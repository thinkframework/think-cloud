package io.github.thinkframework.cloud.client.serviceregistry;

import io.github.thinkframework.cloud.client.commons.Lifecycle;

import java.util.Map;

/**
 * 向服务中心注册和注销实例
 * @param <R>
 * @see org.springframework.cloud.client.serviceregistry.ServiceRegistry
 */
public interface ServiceRegistry<R extends Registration> {

    /**
     * Registers the registration. A registration typically has information about an
     * instance, such as its hostname and port.
     * @param registration registration meta data
     */
    R register(R registration);

    /**
     * Deregisters the registration.
     * @param registration registration meta data
     */
    void deregister(R registration);

    /**
     * Closes the ServiceRegistry. This is a lifecycle method.
     */
    void close();

    /**
     * Sets the status of the registration. The status values are determined by the
     * individual implementations.
     * @param registration The registration to update.
     * @param status The status to set.
     * @see org.springframework.cloud.client.serviceregistry.endpoint.ServiceRegistryEndpoint
     */
    void setStatus(R registration, String status);

/**
 * Gets the status of a particular registration.
 * @param registration The registration to query.
 * @param <T> The type of the status.
 * @return The status of the registration.
 * @see org.springframework.cloud.client.serviceregistry.endpoint.ServiceRegistryEndpoint
 */
    <T> T getStatus(R registration);
}
