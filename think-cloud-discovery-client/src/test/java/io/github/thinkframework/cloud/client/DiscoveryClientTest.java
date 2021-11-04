package io.github.thinkframework.cloud.client;

import io.github.thinkframework.cloud.client.discovery.*;

import java.util.HashMap;
import java.util.UUID;

/**
 * ribbon原生调用
 * @author lixiaobin
 * @version 2.0.0
 * @since 2.0.0
 */
public class DiscoveryClientTest {

    public static void main(String[] args) throws Exception {
        DefaultInstanceConfig config = new DefaultInstanceConfig();
        config.setInstanceId(UUID.randomUUID().toString());
        config.setServiceId("helloword");
        config.setHost("127.0.0.1");
        config.setPort(8080);
        config.setSecure(false);
        config.setMetadata(new HashMap<>());
        DefaultServiceInstance instance = new DefaultServiceInstance.Builder().config(config).build();
//        ThinkDiscoveryClient discoveryClient = new ThinkDiscoveryClientImpl();
        ServiceRegistryHttpClient client = new MockServiceRegistryHttpClient(instance);

        ClientConfig clientConfig = new MockClientConfig();
        InstanceConfig instanceConfig = new MockInstanceConfig();

        DefaultDiscoveryClient discoveryClient = new DefaultDiscoveryClient(clientConfig,
            instanceConfig,
            instance,
            client);

        Thread.sleep(90 * 1000L); // 90s后结束,一个注册更新周期
    }
}
