package io.github.thinkframework.cloud.client.discovery;

/**
 * 客户端配置
 */
public interface ClientConfig {

    /**
     * 服务端地址
     * @return
     */
    String getServiceUrl();

    /**
     * 注册表获取间隔
     * @return 秒
     */
    long getRegistryFetchIntervalSeconds();
}
