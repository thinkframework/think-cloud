package io.github.thinkframework.cloud.client.serviceregistry;

public class DefaultServiceConfig implements ServiceConfig{

    private Long expectedClientRenewalIntervalSeconds;

    @Override
    public long getExpectedClientRenewalIntervalSeconds() {
        return 0;
    }

    public void setExpectedClientRenewalIntervalSeconds(Long expectedClientRenewalIntervalSeconds) {
        this.expectedClientRenewalIntervalSeconds = expectedClientRenewalIntervalSeconds;
    }
}
