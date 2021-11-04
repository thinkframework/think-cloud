package io.github.thinkframework.cloud.client.discovery;

import java.util.Map;

/**
 * 实例配置
 */
public interface InstanceConfig {

    String getInstanceId();

    String getServiceId();

    String getHost();

    int getPort();

    boolean isSecure();

    Map<String, String> getMetadata();

    /**
     * 租约更新间隔
     * @return 秒
     */
    long getLeaseRenewalIntervalInSeconds();

    /**
     * 租约到期持续时间
     * @return
     */
    long getLeaseExpirationDurationInSeconds();
}
