package io.github.thinkframework.cloud.client.discovery;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("think.cloud.instance")
public class SpringInstanceConfig extends AbstractInstanceConfig implements InstanceConfig{
    private long leaseRenewalIntervalInSeconds;
    private long leaseExpirationDurationInSeconds;

    @Override
    public long getLeaseRenewalIntervalInSeconds() {
        return leaseRenewalIntervalInSeconds;
    }

    public void setLeaseRenewalIntervalInSeconds(long leaseRenewalIntervalInSeconds) {
        this.leaseRenewalIntervalInSeconds = leaseRenewalIntervalInSeconds;
    }

    @Override
    public long getLeaseExpirationDurationInSeconds() {
        return leaseExpirationDurationInSeconds;
    }

    public void setLeaseExpirationDurationInSeconds(long leaseExpirationDurationInSeconds) {
        this.leaseExpirationDurationInSeconds = leaseExpirationDurationInSeconds;
    }
}
