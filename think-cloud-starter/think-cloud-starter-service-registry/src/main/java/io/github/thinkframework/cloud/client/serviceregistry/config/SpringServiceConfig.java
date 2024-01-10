package io.github.thinkframework.cloud.client.serviceregistry.config;

import io.github.thinkframework.cloud.client.serviceregistry.ServiceConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("think.cloud.server")
public class SpringServiceConfig implements ServiceConfig {

    private Long id;
    private Long expectedClientRenewalIntervalSeconds;

    @Override
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public long getExpectedClientRenewalIntervalSeconds() {
        return expectedClientRenewalIntervalSeconds;
    }

    public void setExpectedClientRenewalIntervalSeconds(Long expectedClientRenewalIntervalSeconds) {
        this.expectedClientRenewalIntervalSeconds = expectedClientRenewalIntervalSeconds;
    }
}
