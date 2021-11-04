package io.github.thinkframework.cloud.client.discovery;

import io.github.thinkframework.cloud.client.serviceregistry.Registration;

import java.net.URI;

public class DefaultServiceInstance extends AbstractServiceInstance implements Registration {

    public DefaultServiceInstance() {

    }

    @Override
    public URI getUri() {
        // FIXME 先随便写死
        return URI.create("http://www.baidu.com");
    }

    public static class Builder {
        private InstanceConfig config;

        public Builder config(InstanceConfig config) {
            this.config = config;
            return this;
        }

        public DefaultServiceInstance build(){
            DefaultServiceInstance instance = new DefaultServiceInstance();
            instance.setInstanceId(config.getInstanceId());
            instance.setServiceId(config.getServiceId());
            instance.setHost(config.getHost());
            instance.setPort(config.getPort());
            instance.setSecure(config.isSecure());
            instance.setMetadata(config.getMetadata());
            return instance;
        }
    }

    @Override
    public String toString() {
        return "ThinkServiceInstance{" +
            "instanceId='" + instanceId + '\'' +
            ", serviceId='" + serviceId + '\'' +
            ", host='" + host + '\'' +
            ", port=" + port +
            ", secure=" + secure +
            ", metadata=" + metadata +
            '}';
    }
}
