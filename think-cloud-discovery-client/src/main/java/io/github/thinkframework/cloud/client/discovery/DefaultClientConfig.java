package io.github.thinkframework.cloud.client.discovery;

public class DefaultClientConfig implements ClientConfig{
    @Override
    public String getServiceUrl() {
        return null;
    }

    @Override
    public long getRegistryFetchIntervalSeconds() {
        return 0;
    }
}
