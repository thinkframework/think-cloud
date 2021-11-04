package io.github.thinkframework.cloud.client.serviceregistry;

import org.springframework.cloud.client.serviceregistry.Registration;

public class SpringServiceRegistry implements org.springframework.cloud.client.serviceregistry.ServiceRegistry
    <org.springframework.cloud.client.serviceregistry.Registration> {

    @Override
    public void register(Registration registration) {

    }

    @Override
    public void deregister(Registration registration) {

    }

    @Override
    public void close() {

    }

    @Override
    public void setStatus(Registration registration, String status) {

    }

    @Override
    public <T> T getStatus(Registration registration) {
        return null;
    }
}
