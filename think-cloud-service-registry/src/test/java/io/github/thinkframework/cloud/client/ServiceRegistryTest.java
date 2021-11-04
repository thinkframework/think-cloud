package io.github.thinkframework.cloud.client;

import io.github.thinkframework.cloud.client.discovery.DefaultInstanceConfig;
import io.github.thinkframework.cloud.client.discovery.DefaultServiceInstance;
import io.github.thinkframework.cloud.client.serviceregistry.MockServiceConfig;
import io.github.thinkframework.cloud.client.serviceregistry.DefaultServiceRegistry;
import io.github.thinkframework.cloud.client.serviceregistry.web.rest.impl.ApplicationsResourceImpl;
import io.github.thinkframework.cloud.client.serviceregistry.web.rest.impl.ServiceInstanceResourceImpl;

import java.util.HashMap;
import java.util.UUID;

/**
 * ribbon原生调用
 * @author lixiaobin
 * @version 2.0.0
 * @since 2.0.0
 */
public class ServiceRegistryTest {

    private static void setup(){

    }

    public static void main(String[] args) throws Exception {

        DefaultInstanceConfig config = new DefaultInstanceConfig();
        config.setInstanceId(UUID.randomUUID().toString());
        config.setServiceId("helloword");
        config.setHost("127.0.0.1");
        config.setPort(8080);
        config.setSecure(false);
        config.setMetadata(new HashMap<>());
        DefaultServiceInstance serviceInstance = new DefaultServiceInstance.Builder().config(config).build();

        DefaultServiceRegistry serviceRegistry = new DefaultServiceRegistry(new MockServiceConfig());
        serviceRegistry.start();

        ServiceInstanceResourceImpl serviceInstanceResource = new ServiceInstanceResourceImpl(serviceRegistry);

        ApplicationsResourceImpl applicationsResource = new ApplicationsResourceImpl(serviceRegistry);

        serviceInstanceResource.register(serviceInstance);

        for (int i=0;i<3;i++){
            serviceInstanceResource.renew(serviceInstance);
            System.out.println("拉取注册表" + applicationsResource.getApplications());
            Thread.sleep(5 * 1000L);
        }

    }
}
