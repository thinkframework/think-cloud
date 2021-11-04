package io.github.thinkframework.cloud.client.discovery;

import io.github.thinkframework.cloud.client.ServiceInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象服务实例
 */
public abstract class AbstractInstanceConfig implements InstanceConfig {

    protected String instanceId;
    protected String serviceId;
    protected String host;
    protected int port;
    protected boolean secure;
    protected Map<String, String> metadata = new HashMap<>();

    @Override
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    @Override
    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

}
