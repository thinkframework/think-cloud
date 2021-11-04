package io.github.thinkframework.cloud.client.appinfo;

import io.github.thinkframework.cloud.client.ServiceInstance;
import io.github.thinkframework.cloud.client.discovery.DefaultServiceInstance;

import java.util.Map;

/**
 * 应用信息
 */
public class Application {
    private Map<String, DefaultServiceInstance> instances;

    public Application() {
    }

    public Application(Map<String, DefaultServiceInstance> instances) {
        this.instances = instances;
    }

    public Map<String, DefaultServiceInstance> getInstances() {
        return instances;
    }

    public void setInstances(Map<String, DefaultServiceInstance> instances) {
        this.instances = instances;
    }
}
