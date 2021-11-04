package io.github.thinkframework.cloud.client.serviceregistry;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("think.cloud.server")
public class SpringServiceConfig implements ServiceConfig {

    private Long expectedClientRenewalIntervalSeconds;

    @Override
    public long getExpectedClientRenewalIntervalSeconds() {
        return expectedClientRenewalIntervalSeconds;
    }

    public void setExpectedClientRenewalIntervalSeconds(Long expectedClientRenewalIntervalSeconds) {
        this.expectedClientRenewalIntervalSeconds = expectedClientRenewalIntervalSeconds;
    }
}
