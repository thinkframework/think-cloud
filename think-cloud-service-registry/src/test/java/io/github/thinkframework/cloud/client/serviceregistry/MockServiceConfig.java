package io.github.thinkframework.cloud.client.serviceregistry;

public class MockServiceConfig implements ServiceConfig{

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public long getExpectedClientRenewalIntervalSeconds() {
        return 30L;
    }
}
