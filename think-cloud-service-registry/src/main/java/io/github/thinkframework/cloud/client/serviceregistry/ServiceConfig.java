package io.github.thinkframework.cloud.client.serviceregistry;

public interface ServiceConfig {

    /**
     * 预期客户端更新间隔
     * @return 秒
     */
    long getExpectedClientRenewalIntervalSeconds();
}
