package io.github.thinkframework.cloud.client.discovery;

public class MockClientConfig implements ClientConfig{
    @Override
    public String getServiceUrl() {
        return "http://localhost:8761";
    }

    @Override
    public long getRegistryFetchIntervalSeconds() {
        return 30L;
    }
}
