package io.github.thinkframework.cloud.client.discovery;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("think.cloud.client")
public class SpringClientConfig implements ClientConfig{
    private String serviceUrl;
    private long registryFetchIntervalSeconds;
    @Override
    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    @Override
    public long getRegistryFetchIntervalSeconds() {
        return registryFetchIntervalSeconds;
    }


    public void setRegistryFetchIntervalSeconds(long registryFetchIntervalSeconds) {
        this.registryFetchIntervalSeconds = registryFetchIntervalSeconds;
    }

}
