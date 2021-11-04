package io.github.thinkframework.cloud.client;

import java.net.URI;
import java.util.Map;

/**
 * 服务实例
 * @see org.springframework.cloud.client.ServiceInstance
 * @author hdhxby
 */
public interface ServiceInstance {

    /**
     * 实例id
     * @return 实例id
     */
    default String getInstanceId() {
        return null;
    }

    /**
     * 服务id
     * @return 服务id
     */
    String getServiceId();

    /**
     * 主机名称
     * @return 主机名称
     */
    String getHost();

    /**
     * 端口
     * @return 端口
     */
    int getPort();

    /**
     * 是否HTTPS
     * @return 是否HTTPS
     */
    boolean isSecure();

    /**
     * URI
     * @return URI
     */
    URI getUri();

    /**
     * 元数据
     * @return 元数据
     */
    Map<String, String> getMetadata();

    /**
     * @return The scheme of the service instance.
     */
    default String getScheme() {
        return getUri().getScheme();
    }
}
