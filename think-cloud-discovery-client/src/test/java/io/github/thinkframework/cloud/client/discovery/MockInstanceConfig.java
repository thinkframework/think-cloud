package io.github.thinkframework.cloud.client.discovery;

public class MockInstanceConfig extends AbstractInstanceConfig implements InstanceConfig{
    @Override
    public long getLeaseRenewalIntervalInSeconds() {
        return 30L;
    }

    @Override
    public long getLeaseExpirationDurationInSeconds() {
        return 90L;
    }
}
