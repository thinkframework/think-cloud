package io.github.thinkframework.cloud.client.serviceregistry;

public class MockServiceConfig implements ServiceConfig{

    @Override
    public long getExpectedClientRenewalIntervalSeconds() {
        return 30L;
    }
}
