package io.github.thinkframework.cloud.client.serviceregistry;

public interface ServiceConfig {

    /**
     * id
     * @return
     */
    long getId();

    /**
     * 预期客户端更新间隔
     * @return 秒
     */
    long getExpectedClientRenewalIntervalSeconds();
}
