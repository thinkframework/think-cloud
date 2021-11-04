package io.github.thinkframework.cloud.client.discovery;

import io.github.thinkframework.cloud.client.appinfo.Applications;
import io.github.thinkframework.cloud.client.serviceregistry.Registration;

import java.util.Map;

/**
 * 向服务中心注册和注销实例客户端
 * @param <R>
 * @see com.netflix.discovery.shared.transport.EurekaHttpClient
 */
public interface ServiceRegistryHttpClient<R extends Registration> {

    /**
     * 注册
     * @param registration registration meta data
     */
    void register(R registration);

    /**
     * 续约
     * @param registration renew meta data
     */
    void renew(R registration);

    /**
     * 取消注册
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

    /**
     * 全量更新
     */
    Applications getApplications();

    /**
     * 增量更新
     */
    Applications getDelta();
}
