package io.github.thinkframework.cloud.client.serviceregistry;

import io.github.thinkframework.cloud.client.discovery.DiscoveryClient;
import io.github.thinkframework.cloud.client.discovery.DefaultDiscoveryClient;

import javax.annotation.PostConstruct;

public class ThinkServiceRegistryBootStrap {

    private DefaultServiceRegistry registry;

    private DefaultDiscoveryClient client;

    public ThinkServiceRegistryBootStrap(ServiceRegistry registry, DiscoveryClient client) {
        // TODO 考虑下方法签名的设计
        this.registry = (DefaultServiceRegistry) registry;
        this.client = (DefaultDiscoveryClient) client;
    }

    @PostConstruct
    protected void init(){
        // 启动注册中心
        if(registry != null) {

        }
        // 启动客户端
        if(client != null) {

        }
    }
}
