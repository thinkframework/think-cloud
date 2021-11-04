package io.github.thinkframework.cloud.client.discovery;

import java.util.HashMap;
import java.util.Map;

public class DefaultInstanceConfig extends AbstractInstanceConfig implements InstanceConfig{

    @Override
    public long getLeaseRenewalIntervalInSeconds() {
        return 0;
    }

    @Override
    public long getLeaseExpirationDurationInSeconds() {
        return 0;
    }
}
